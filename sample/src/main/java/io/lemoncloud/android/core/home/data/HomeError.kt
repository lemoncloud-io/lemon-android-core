package io.lemoncloud.android.core.home.data

import io.lemoncloud.core.architecture.ui.BaseError

data class HomeError(
    override val message: String,
    override val exception: Throwable?,
) : BaseError
