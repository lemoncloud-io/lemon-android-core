package io.lemon.android.ui.home.data

import io.lemon.android.core.ui.BaseState

data class HomeState(
    override val isLoading: Boolean = false,
    val count: Int = 0,
) : BaseState