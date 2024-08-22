package io.lemon.android.core.ui.architecture

/**
 * [BaseEvent]
 *
 * UI에서 사용될 이벤트를 관리합니다.
 *
 * BaseEvent를 확장한 sealed interface 에 UI에서 사용할 이벤트들을 정의하여 사용합니다.
 *
 * [BaseViewModel.bindEvent] 여러가지의 이벤트를 핸들링 하여 처리할 때 사용합니다.
 *
 * [BaseViewModel.onEvent] event 플로우에 이벤트를 전달합니다.
 *
 * @author raine@lemoncloud.io
 **/
interface BaseEvent