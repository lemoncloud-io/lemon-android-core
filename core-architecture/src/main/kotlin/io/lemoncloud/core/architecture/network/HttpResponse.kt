package io.lemoncloud.core.architecture.network

import io.lemoncloud.core.architecture.error.NetworkException.HttpException
import io.lemoncloud.core.architecture.error.NetworkException.ResponseNullPointerException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * retrofit 을 통해 반환되는 네트워크 응답 개체를 구조화합니다.
 *
 * 네트워크 통신 결과에 따라 `Success` 또는 `Fail`이 반환 됩니다.
 */
sealed interface HttpResponse<out T : Any?> {
    /**
     * 네트워크 통신 성공 시에 반환됩니다
     * @property data 네트워크 통신 결과 데이터
     */
    class Success<T>(
        val data: T,
    ) : HttpResponse<T>

    /**
     * 네트워크 통신 실패 시에 반환됩니다
     * @property code 네트워크 통신 결과 코드
     * @property error 네트워크 통신 결과 에러
     */
    class Fail<T>(
        val error: Throwable,
        val code: Int? = null,
    ) : HttpResponse<T>

    companion object {

        /**
         * [Response]를 반환하는 메서드를 전달받아 [HttpResponse] 타입의 Flow로 변환합니다.
         * @param call `Response`타입의 반환 값을 전달받아야만 합니다.
         */
        suspend fun <T> valueOf(call: (suspend () -> Response<T>)): Flow<HttpResponse<T>> =
            flowOf(
                runCatching { call.invoke() }.fold(
                    onSuccess = {
                        if (it.isSuccessful) Success(data = it.body()!!)
                        else Fail(
                            code = it.code(),
                            error = HttpException(message = ("[${it.code()}]" + it.errorBody()?.toString()))
                        )
                    },
                    onFailure = { exception ->
                        when (exception) {
                            is NullPointerException -> Fail(error = ResponseNullPointerException())
                            else -> Fail(error = HttpException(cause = exception, message = exception.message))
                        }
                    })
            ).flowOn(Dispatchers.IO)

        /**
         * [Response]를 반환하는 메서드를 전달한 뒤, Unit을 포함한 [HttpResponse] 타입의 Flow로 변환합니다.
         * [Response]에 상관 없이, Unit을 반환하고 싶은 경우에 사용할 수 있습니다.
         *
         * 예를 들어, `HEAD` 메서드와 같이 특정한 반환 값이 없는 경우 사용합니다.
         * ```
         * interface MockService {
         *     @HEAD("mock")
         *     suspend fun head(): Response<Void>
         * }
         * ```
         * 서비스가 위와 같이 정의되었다고 가정 하였을 때, [HttpResponse] 인터페이스를 생성하는 방법은 아래와 같습니다.
         * ```
         * val response = HttpResponse.createEmpty { networkResponse }
         * ```
         */
        suspend fun <T> valueOfWithEmpty(call: (suspend () -> Response<T>)): Flow<HttpResponse<Unit>> =
            flowOf(
                runCatching { call.invoke() }.fold(
                    onSuccess = {
                        if (it.isSuccessful) Success(data = Unit)
                        else Fail(
                            code = it.code(),
                            error = HttpException(message = ("[${it.code()}]" + it.errorBody()?.toString()))
                        )
                    },
                    onFailure = { exception ->
                        Fail(
                            error = HttpException(
                                cause = exception,
                                message = exception.message
                            )
                        )
                    }
                )
            ).flowOn(Dispatchers.IO)

        /**
         * Nullable한 [Response]를 반환하는 메서드를 전달받아 [HttpResponse] 타입의 Flow로 변환합니다.
         * @param call `Response`형태의 반환 값을 전달받아야만 합니다
         */
        suspend fun <T> valueOfWithNull(call: (suspend () -> Response<T>)): Flow<HttpResponse<T?>> =
            flowOf(
                runCatching { call.invoke() }.fold(
                    onSuccess = {
                        if (it.isSuccessful) Success(data = it.body())
                        else Fail(
                            code = it.code(),
                            error = HttpException(message = ("[${it.code()}]" + it.errorBody()?.toString()))
                        )
                    },
                    onFailure = { exception ->
                        Fail(
                            error = HttpException(
                                cause = exception,
                                message = exception.message
                            )
                        )
                    }
                )
            ).flowOn(Dispatchers.IO)

        /**
         *  `BaseDto`를 포함하고 있는 [HttpResponse] 타입을 [Result]로 변환합니다.
         *  추가적인 매핑 과정이 없는 상황에서 네트워크 레이어의 DTO 데이터를 빠르게 도메인 모델로 변경 하고자 할 때 사용합니다.
         *  @see [BaseDto.toModel]
         */
        fun <Dto : BaseDto<Model>, Model : Any> to(httpResponse: HttpResponse<Dto>): Result<Model> =
            when (httpResponse) {
                is Fail -> Result.failure(exception = httpResponse.error)
                is Success -> Result.success(value = httpResponse.data.toModel())
            }

        /**
         *  `BaseDto`를 포함하고 있는 [HttpResponse] 리스트 타입을 [Result] 리스트로 변환합니다.
         *  추가적인 매핑 과정이 없는 상황에서 네트워크 레이어의 DTO 데이터를 빠르게 도메인 모델로 변경 하고자 할 때 사용합니다.
         *  @see [BaseDto.toModel]
         */
        fun <Dto : BaseDto<Model>, Model : Any> to(httpResponse: HttpResponse<List<Dto>>): Result<List<Model>> =
            when (httpResponse) {
                is Fail -> Result.failure(exception = httpResponse.error)
                is Success -> Result.success(value = httpResponse.data.map { it.toModel() })
            }

        /**
         *  [HttpResponse] 타입을 [Result]로 변환합니다.
         *  @param mapper 어떠한 방식으로 변환할 지에 대한 매퍼 람다식을 정의합니다.
         */
        fun <In, Out> to(
            httpResponse: HttpResponse<In>,
            mapper: (In) -> Out
        ): Result<Out> = when (httpResponse) {
            is Fail -> Result.failure(exception = httpResponse.error)
            is Success -> Result.success(mapper(httpResponse.data))
        }
    }
}
