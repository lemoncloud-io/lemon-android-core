package io.lemoncloud.android.buildSystem.plugin

import io.lemoncloud.android.buildSystem.util.Extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                add("ksp", versionCatalog.findLibrary("hilt.compiler").get())
                add("ksp", versionCatalog.findLibrary("androidx-hilt-ext-compiler").get())
                add("implementation", versionCatalog.findLibrary("hilt-android").get())
                add("implementation", versionCatalog.findLibrary("androidx-hilt-ext-work").get())
                add("implementation", versionCatalog.findLibrary("androidx-hilt-navigation-compose").get())
                add("implementation", versionCatalog.findLibrary("hilt-android-testing").get())
            }
        }
    }
}
