package io.lemon.android.core.home.data

import io.lemon.android.core.ui.architecture.BaseError

data class HomeError(
    override val message: String,
    override val exception: Throwable?,
) : BaseError