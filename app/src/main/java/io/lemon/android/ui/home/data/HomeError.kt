package io.lemon.android.ui.home.data

import io.lemon.android.core.ui.BaseError

data class HomeError(
    override val message: String,
    override val exception: Throwable?,
) : BaseError