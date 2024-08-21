package io.lemon.android.ui.home.data

import io.lemon.android.core.ui.BaseEvent

sealed interface HomeEvent : BaseEvent {

    data object OnClickCountUpButton : HomeEvent
    data object OnClickCountDownButton : HomeEvent
    data object OnClickNavigateButton : HomeEvent
}