package io.lemoncloud.core.architecture.ui

/**
 * [ScreenState]
 * - 전체적인 화면 상태 정의
 * - [UiState] 와 함께 복합적으로 제어하여 사용 가능
 *
 * @property INITIAL 초기 상태
 * @property LOADING 로딩
 * @property SUCCESS 성공
 * @property FAILURE 실패
 * @property EMPTY 성공하였지만, 화면에 보여줄 데이터가 비어있는 상태
 * @property OFFLINE 오프라인
 * @property TIMEOUT 타임아웃
 */
enum class ScreenState {
    INITIAL,
    LOADING,
    SUCCESS,
    FAILURE,
    EMPTY,
    OFFLINE,
    TIMEOUT,
}
