package io.lemoncloud.core.architecture.ui

/**
 * [BaseState]
 *
 * UI의 상태 정보 정의
 *
 * 해당 인터페이스를 확장하여 상태를 정의하여 사용할 것
 *
 * [BaseViewModel.updateState] 를 사용하여 상태를 업데이트 합니다.
 *
 * [BaseViewModel.state] 를 관측하여 상태를 UI 상에 반영할 수 있습니다.
 *
 * @property uiState 복합 ui 상태 관리
 * @property screenState 단일 화면 상태 관리
 * @see ScreenState
 * @see UiState
 */
interface BaseState {
    val uiState : UiState
    var screenState: ScreenState
}
