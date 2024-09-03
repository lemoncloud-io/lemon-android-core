package io.lemoncloud.android.buildSystem.extensions

import com.android.build.api.dsl.CommonExtension
import io.lemoncloud.android.buildSystem.util.Extensions.versionCatalog
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.extensionAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {

        buildFeatures { compose = true }

        dependencies {
            add("implementation", versionCatalog.findLibrary("androidx-compose-animation").get())
            add("implementation", versionCatalog.findLibrary("androidx-compose-foundation").get())
            add("implementation", versionCatalog.findLibrary("androidx.compose.ui").get())
            add("implementation", versionCatalog.findLibrary("androidx.compose.ui-graphics").get())
            add("implementation", versionCatalog.findLibrary("androidx.compose.ui-test").get())
            add("implementation", versionCatalog.findLibrary("androidx-compose-ui-tooling").get())
            add("implementation", versionCatalog.findLibrary("androidx-compose-ui-tooling-preview").get())
            add("implementation", versionCatalog.findLibrary("androidx-compose-material").get())
            add("implementation", versionCatalog.findLibrary("androidx-compose-material3").get())
            add("implementation", versionCatalog.findLibrary("androidx-compose-material-icons-extended").get())
        }
    }
}
