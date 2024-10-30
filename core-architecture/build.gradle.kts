plugins {
    alias(libs.plugins.lemon.android.library)
    alias(libs.plugins.lemon.android.feature)
    alias(libs.plugins.lemon.android.hilt)
    alias(libs.plugins.lemon.android.kotlin)
    alias(libs.plugins.lemon.android.okhttp)
    alias(libs.plugins.lemon.android.test.ext)
}

android {
    namespace = "io.lemoncloud.core.architecture"
}
