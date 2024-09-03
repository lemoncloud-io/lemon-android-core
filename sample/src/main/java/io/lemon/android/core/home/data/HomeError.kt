package io.lemon.android.core.home.data

import io.lemon.core.architecture.ui.BaseError

data class HomeError(
    override val message: String,
    override val exception: Throwable?,
) : BaseError
