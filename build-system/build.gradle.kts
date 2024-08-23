
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
            implementationClass = "io.lemon.android.buildSystem.plugin.AndroidApplicationPlugin"
        }
        register("lemon.android.library") {
            id = "lemon.android.library"
            implementationClass = "io.lemon.android.buildSystem.plugin.AndroidLibraryPlugin"
        }
        register("lemon.android.application.compose") {
            id = "lemon.android.application.compose"
            implementationClass = "io.lemon.android.buildSystem.plugin.AndroidApplicationComposePlugin"
        }
        register("lemon.android.library.compose") {
            id = "lemon.android.library.compose"
            implementationClass = "io.lemon.android.buildSystem.plugin.AndroidLibraryComposePlugin"
        }
        register("lemon.android.compose.ext") {
            id = "lemon.android.compose.ext"
            implementationClass = "io.lemon.android.buildSystem.plugin.AndroidComposeExtPlugin"
        }
        register("lemon.android.datastore") {
            id = "lemon.android.datastore"
            implementationClass = "io.lemon.android.buildSystem.plugin.DataStorePlugin"
        }
        register("lemon.android.feature") {
            id = "lemon.android.feature"
            implementationClass = "io.lemon.android.buildSystem.plugin.AndroidFeaturePlugin"
        }
        register("lemon.android.hilt") {
            id = "lemon.android.hilt"
            implementationClass = "io.lemon.android.buildSystem.plugin.HiltPlugin"
        }
        register("lemon.android.kotlin") {
            id = "lemon.android.kotlin"
            implementationClass = "io.lemon.android.buildSystem.plugin.KotlinPlugin"
        }
        register("lemon.android.okhttp") {
            id = "lemon.android.okhttp"
            implementationClass = "io.lemon.android.buildSystem.plugin.OkhttpPlugin"
        }
        register("lemon.android.room") {
            id = "lemon.android.room"
            implementationClass = "io.lemon.android.buildSystem.plugin.RoomPlugin"
        }
        register("lemon.android.test.ext") {
            id = "lemon.android.test.ext"
            implementationClass = "io.lemon.android.buildSystem.plugin.TestExtPlugin"
        }
    }
}