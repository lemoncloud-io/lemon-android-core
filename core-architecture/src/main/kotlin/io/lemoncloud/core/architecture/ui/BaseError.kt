package io.lemoncloud.core.architecture.ui

import android.util.Log

/**
 * [BaseError]
 *
 * 오류 상태
 *
 * 상태 변화 및 사이드 이펙트에 발생하는 오류를 나타냅니다.
 *
 * 만약 오류에 부가적인 정보를 포함하고 싶을 경우 해당 인터페이스를 확장하여 구현할 수 있습니다.
 *
 * @property message 오류 메시지
 * @property exception 오류 정보 (특정한 Throwable이 존재하지 않을 경우 null을 사용할 수 있습니다.)
 */
interface BaseError {
    val message: String?
    val exception: Throwable?

    fun printErrorStackTrace() {
        exception?.printStackTrace()
    }

    fun printErrorLog(tag: String) {
        Log.e(
            tag,
            """
                message: $message
                exceptionMessage: ${exception?.message}
                exceptionCause: ${exception?.cause}
            """
                .trimIndent()
        )
    }
}
