package io.lemon.android.core.home.data

import io.lemon.android.core.ui.architecture.BaseState


data class HomeState(
    override val isLoading: Boolean = false,
    val count: Int = 0,
) : BaseState