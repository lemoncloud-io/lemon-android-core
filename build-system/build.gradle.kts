
plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.android.gradle)
    compileOnly(libs.compose.gradle)
    compileOnly(libs.ksp.gradle)
}

gradlePlugin {
    plugins {
        register("lemon.android.application") {
            id = "lemon.android.application"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.AndroidApplicationPlugin"
        }
        register("lemon.android.library") {
            id = "lemon.android.library"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.AndroidLibraryPlugin"
        }
        register("lemon.android.application.compose") {
            id = "lemon.android.application.compose"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.AndroidApplicationComposePlugin"
        }
        register("lemon.android.library.compose") {
            id = "lemon.android.library.compose"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.AndroidLibraryComposePlugin"
        }
        register("lemon.android.compose.ext") {
            id = "lemon.android.compose.ext"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.AndroidComposeExtPlugin"
        }
        register("lemon.android.datastore") {
            id = "lemon.android.datastore"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.DataStorePlugin"
        }
        register("lemon.android.feature") {
            id = "lemon.android.feature"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.AndroidFeaturePlugin"
        }
        register("lemon.android.hilt") {
            id = "lemon.android.hilt"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.HiltPlugin"
        }
        register("lemon.android.kotlin") {
            id = "lemon.android.kotlin"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.KotlinPlugin"
        }
        register("lemon.android.okhttp") {
            id = "lemon.android.okhttp"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.OkhttpPlugin"
        }
        register("lemon.android.room") {
            id = "lemon.android.room"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.RoomPlugin"
        }
        register("lemon.android.test.ext") {
            id = "lemon.android.test.ext"
            implementationClass = "io.lemoncloud.android.buildSystem.plugin.TestExtPlugin"
        }
    }
}
