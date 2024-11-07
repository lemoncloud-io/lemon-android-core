plugins {
    alias(libs.plugins.lemon.android.library)
    alias(libs.plugins.lemon.android.feature)
    alias(libs.plugins.lemon.android.kotlin)
    alias(libs.plugins.lemon.android.test.ext)
}

android {
    namespace = "io.lemoncloud.core.android"

    defaultConfig {
        testInstrumentationRunner = "io.lemoncloud.core.android.test.TestRunner"
    }
}
