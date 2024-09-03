package io.lemon.android.core.home.data

import io.lemon.core.architecture.ui.BaseEvent

sealed interface HomeEvent : BaseEvent {

    data object OnClickCountUpButton : HomeEvent

    data object OnClickCountDownButton : HomeEvent
}
