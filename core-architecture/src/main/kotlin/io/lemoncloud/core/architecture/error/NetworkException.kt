package io.lemoncloud.core.architecture.error

object NetworkException {

    /**
     * HTTP 통신 과정에서 발생하는 예외
     */
    class HttpException(
        override val cause: Throwable? = null,
        override val message: String? = null,
    ) : Throwable()

    /**
     * HTTP 결과 값을 파싱하는 과정에서 Response가 null일 경우 발생하는 예외
     */
    class ResponseNullPointerException(
        override val cause: Throwable = NullPointerException(),
        override val message: String = "Response body is null. If you received an empty body response, you can handle this by using createEmpty().",
    ) : Throwable()
}
