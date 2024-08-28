plugins {
    alias(libs.plugins.lemon.android.library)
    alias(libs.plugins.lemon.android.library.compose)
    alias(libs.plugins.lemon.android.compose.ext)
    alias(libs.plugins.lemon.android.feature)
    alias(libs.plugins.lemon.android.kotlin)
}

android {
    namespace = "io.lemon.core.compose.util"
    group = "io.lemon.core"
}
