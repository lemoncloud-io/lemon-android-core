package io.lemoncloud.android.core.home.data

import io.lemoncloud.core.architecture.ui.BaseEvent

sealed interface HomeEvent : BaseEvent {

    data object OnClickCountUpButton : HomeEvent

    data object OnClickCountDownButton : HomeEvent
}
