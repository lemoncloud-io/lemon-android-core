package io.lemoncloud.android.core.home.data

import io.lemoncloud.core.architecture.ui.BaseEffect

sealed interface HomeEffect : BaseEffect {
    data class ShowSnackbar(val message: String) : HomeEffect
}
