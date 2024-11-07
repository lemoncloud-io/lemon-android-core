package io.lemoncloud.core.architecture.network

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * 네트워크 통신 과정에서의 데이터를 json 기반으로 변환하는 클래스
 * @param json 인코딩 및 디코딩을 통해 json 직렬화를 도와줍니다.
 * @see [JsonConverterFactory.Default]
 */
class JsonConverterFactory(
    private val json: Json
) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return Converter { body ->
            try {
                if (body.contentLength() == 0L) null
                else {
                    val serializer = json.serializersModule.serializer(type)
                    json.decodeFromString(serializer, body.string())
                }
            } catch (e: Throwable) {
                throw RuntimeException(e)
            }
        }
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        return Converter { value: Any ->
            try {
                val serializer = json.serializersModule.serializer(type)
                val jsonString = json.encodeToString(serializer, value)
                jsonString.toRequestBody("application/json".toMediaTypeOrNull())
            } catch (e: Throwable) {
                throw RuntimeException(e)
            }
        }
    }

    companion object {
        val Default: JsonConverterFactory = JsonConverterFactory(
            Json {
                ignoreUnknownKeys = true // Json 문자열 중 serializable 클래스에 없는 필드가 존재한다면 에러를 호출하지 않고 무시합니다.
                coerceInputValues = true // Json 문자열 필드와 serializable 클래스의 필드 타입이 매칭되지 않는다면, 기본 값으로 대체합니다.
            })
    }
}


