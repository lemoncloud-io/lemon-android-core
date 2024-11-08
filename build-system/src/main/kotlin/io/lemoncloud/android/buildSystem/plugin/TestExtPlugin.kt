package io.lemoncloud.android.buildSystem.plugin

import io.lemoncloud.android.buildSystem.util.Extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestExtPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", versionCatalog.findLibrary("junit").get())
                add("implementation", versionCatalog.findLibrary("truth").get())
                add("implementation", versionCatalog.findLibrary("dexmaker-mockito").get())
                add("implementation", versionCatalog.findLibrary("mockk").get())
                add("implementation", versionCatalog.findLibrary("mockk-android").get())
            }
        }
    }
}
