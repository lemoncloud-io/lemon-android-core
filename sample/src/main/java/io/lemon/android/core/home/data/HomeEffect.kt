package io.lemon.android.core.home.data

import io.lemon.android.core.ui.architecture.BaseEffect

sealed interface HomeEffect : BaseEffect {
    data class ShowSnackbar(val message: String) : HomeEffect
}