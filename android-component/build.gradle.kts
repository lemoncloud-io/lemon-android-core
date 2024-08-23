plugins {
    alias(libs.plugins.lemon.android.library)
    alias(libs.plugins.lemon.android.feature)
    alias(libs.plugins.lemon.android.kotlin)
}

android {
    namespace = "io.lemon.core.android.component"
    group = "io.lemon.core"
}
