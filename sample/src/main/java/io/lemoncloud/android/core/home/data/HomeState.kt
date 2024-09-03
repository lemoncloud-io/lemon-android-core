package io.lemoncloud.android.core.home.data

import io.lemoncloud.core.architecture.ui.BaseState

data class HomeState(
    override val isLoading: Boolean = false,
    val count: Int = 0,
) : BaseState
