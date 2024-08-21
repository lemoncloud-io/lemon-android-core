package io.lemon.android.ui.detail.data

import io.lemon.android.core.ui.BaseError
import io.lemon.android.core.ui.BaseViewModel
import io.lemon.android.core.ui.Config

class DetailViewModel : BaseViewModel<DetailState, DetailEvent, DetailEffect, BaseError>() {
    override fun createInitialState(): DetailState = DetailState(isLoading = false)
    override fun createConfig(): Config<DetailEvent> = Config()
}