package io.lemon.android.ui.home.data

import io.lemon.android.core.ui.BaseEffect

sealed interface HomeEffect : BaseEffect {

    data object NavigateDetail : HomeEffect
    data class ShowSnackbar(val message: String) : HomeEffect
}