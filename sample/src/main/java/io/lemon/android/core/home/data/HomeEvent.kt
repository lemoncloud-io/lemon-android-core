package io.lemon.android.core.home.data

import io.lemon.core.ui.architecture.BaseEvent

sealed interface HomeEvent : BaseEvent {

    data object OnClickCountUpButton : HomeEvent

    data object OnClickCountDownButton : HomeEvent
}
