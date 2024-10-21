package io.lemoncloud.core.architecture.domain

/**
 * 아키텍처 간 데이터 전달 과정에서 발생하는 데이터를 구조화 합니다.
 * @property Success 데이터 전달에 성공한 경우
 * @property Fail 데이터 전달에 실패한 경우; 예외와 관련 메시지를 포함합니다. exceptions은 null이 될 수 있습니다.
 */
sealed interface DataState<out T : Any?> {
    data class Success<T>(val data: T, val message: String? = null) : DataState<T>
    data class Fail(val exceptions: Throwable? = null, val message: String? = null) : DataState<Nothing>
}
