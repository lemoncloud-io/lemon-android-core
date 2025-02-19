package io.lemoncloud.core.architecture.ui

/**
 * [UiState]
 * - 플래그를 바탕으로 복합적인 상태관리
 * - [ScreenState] 와 함께 복합적으로 제어하여 사용 가능
 * @property isFailure 실패 여부
 * @property isLoading 로딩 중
 * @property isInitialLoading 초기 로딩 중
 * @property isRefreshing 리프레시 중
 * @property isPaginating 페이지네이션 중
 * @property isUpdating 업데이트 중
 * @property isSaving 양식 제출 또는 데이터 저장 중중
 * @property isDeleting 삭제 중
 * @property isSearching 검색 중
 * @property isOffline 오프라인
 */
data class UiState(
    val isFailure: Boolean = false,
    val isLoading: Boolean = false,
    val isInitialLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isPaginating: Boolean = false,
    val isUpdating: Boolean = false,
    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val isSearching: Boolean = false,
    val isOffline :Boolean = false
)
