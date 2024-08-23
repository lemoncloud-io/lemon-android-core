package io.lemon.android.buildSystem.plugin

import io.lemon.android.buildSystem.util.Extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class OkhttpPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", versionCatalog.findLibrary("okhttp").get())
                add("implementation", versionCatalog.findLibrary("okhttp-logging").get())
                add("implementation", versionCatalog.findLibrary("okhttp-mock-webserver").get())
                add("implementation", versionCatalog.findLibrary("retrofit").get())
            }
        }
    }
}