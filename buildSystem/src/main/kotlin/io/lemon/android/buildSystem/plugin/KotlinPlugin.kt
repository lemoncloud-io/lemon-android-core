package io.lemon.android.buildSystem.plugin

import io.lemon.android.buildSystem.util.Extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-parcelize")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                add("implementation", versionCatalog.findLibrary("kotlinx-coroutines").get())
                add("implementation", versionCatalog.findLibrary("kotlinx-datetime").get())
                add("implementation", versionCatalog.findLibrary("kotlinx-serialization-json").get())
            }
        }
    }
}
