package io.lemon.android.core.home.data

import io.lemon.core.architecture.ui.BaseEffect

sealed interface HomeEffect : BaseEffect {
    data class ShowSnackbar(val message: String) : HomeEffect
}
