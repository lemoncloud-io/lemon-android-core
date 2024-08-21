package io.lemon.android.buildSystem.plugin

import io.lemon.android.buildSystem.util.Extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                add("ksp", versionCatalog.findLibrary("room.compiler").get())
                add("implementation", versionCatalog.findLibrary("room.runtime").get())
                add("implementation", versionCatalog.findLibrary("room.ktx").get())
            }
        }
    }
}
