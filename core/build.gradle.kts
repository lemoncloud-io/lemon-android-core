plugins {
    alias(libs.plugins.lemon.android.library)
    alias(libs.plugins.lemon.android.library.compose)
    alias(libs.plugins.lemon.android.feature)
    alias(libs.plugins.lemon.android.compose.ext)
    alias(libs.plugins.lemon.android.datastore)
    alias(libs.plugins.lemon.android.okhttp)
    alias(libs.plugins.lemon.android.room)
    alias(libs.plugins.lemon.android.hilt)
    alias(libs.plugins.lemon.android.kotlin)
}

android {
    namespace = "io.lemon.android.core"
}