plugins {
    alias(libs.plugins.lemon.android.library)
    alias(libs.plugins.lemon.android.feature)
    alias(libs.plugins.lemon.android.kotlin)
}

android {
    namespace = "io.lemon.core.ui.architecture"
    group = "io.lemon.core"
}