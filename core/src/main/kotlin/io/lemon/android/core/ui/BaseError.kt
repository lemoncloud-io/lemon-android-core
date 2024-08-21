package io.lemon.android.core.ui

import android.util.Log

/**
 * [BaseError]
 * Error state.
 * You can handle errors that occur during state and side effect changes.
 * if you want to contain additional error info, expand it.
 * @property message error message
 * @property exception throwable
 */
interface BaseError {
    val message: String
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