package io.lemoncloud.core.architecture.network

import io.lemoncloud.core.architecture.domain.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * retrofit 을 통해 반환되는 네트워크 응답 개체를 구조화합니다.
 * 네트워크 통신 결과에 따라 `Success` 또는 `Fail`이 반환됩니다.
 */
sealed interface HttpResponse<out T : Any?> {

    /**
     * 네트워크 통신 성공 시에 반환된다
     * @property data 네트워크 통신 결과 데이터
     */
    data class Success<T>(
        val data: T
    ) : HttpResponse<T>

    /**
     * 네트워크 통신 실패 시에 반환된다
     * @property code 네트워크 통신 결과 코드
     * @property error 네트워크 통신 결과 에러
     * @property message 네트워크 통신 결과 메시지
     */
    data class Fail(
        val code: Int? = null, val error: Throwable? = null, val message: String? = null
    ) : HttpResponse<Nothing>

    companion object {

        /**
         * Response를 반환하는 `Retrofit`의 통신 메서드를 전달받아 `HttpResponse` 타입의 Flow로 변환합니다.
         * @param call 네트워크 통신 메서드 `Response`형태의 반환 값을 전달받아야만한다.
         */
        fun <T> create(call: (suspend () -> Response<T>)): Flow<HttpResponse<T?>> = flow {
            emit(call().runCatching {
                    if (isSuccessful) Success(data = body())
                    else Fail(code = code(), error = null, message = errorBody()?.string())
                }.getOrElse {
                    Fail(error = it.cause, message = it.cause?.message)
                })
        }.flowOn(Dispatchers.IO)

        /**
         *  `BaseDto`를 포함하고 있는 `HttpResponse` 타입을 `DataState`로 변환합니다.
         *  부가적인 가공 과정이 없는 상황에서 네트워크 레이어의 DTO 데이터를 빠르게 도메인 모델로 변경하고자 할 때 사용합니다.
         *  @see [BaseDto.toModel]
         */
        fun <In : BaseDto<Out>, Out : Any> HttpResponse<In>.toDataState(): DataState<Out> = when (this) {
            is Fail -> DataState.Fail(exceptions = this.error, message = this.message)
            is Success -> DataState.Success(data = this.data.toModel())
        }
    }
}
