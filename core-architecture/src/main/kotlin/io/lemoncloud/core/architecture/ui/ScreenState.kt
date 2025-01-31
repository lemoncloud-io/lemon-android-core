package io.lemoncloud.core.architecture.ui

/**
 * [ScreenState]
 * @property INITIAL 초기 상태
 * @property LOADING 로딩
 * @property SUCCESS 성공
 * @property FAILURE 실패
 * @property EMPTY 성공하였지만, 화면에 보여줄 데이터가 비어있는 상태
 * @property PAGINATING 페이징
 * @property REFRESHING 화면 리프레시
 */
enum class ScreenState {
    INITIAL,
    LOADING,
    SUCCESS,
    FAILURE,
    EMPTY,
    PAGINATING,
    REFRESHING
}
