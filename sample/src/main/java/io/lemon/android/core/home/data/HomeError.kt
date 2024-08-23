package io.lemon.android.core.home.data

import io.lemon.core.ui.architecture.BaseError

data class HomeError(
    override val message: String,
    override val exception: Throwable?,
) : BaseError
