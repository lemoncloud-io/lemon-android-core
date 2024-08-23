package io.lemon.android.buildSystem.plugin

import io.lemon.android.buildSystem.util.Extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
                dependencies {
                    add("implementation", versionCatalog.findLibrary("androidx-appcompat").get())
                    add("implementation", versionCatalog.findLibrary("androidx-core-ktx").get())
                    add("implementation", versionCatalog.findLibrary("androidx-core-splashscreen").get())
                    add("implementation", versionCatalog.findLibrary("androidx-lifecycle-service").get())
                    add("implementation", versionCatalog.findLibrary("androidx-lifecycle-viewmodel-ktx").get())
                    add("implementation", versionCatalog.findLibrary("androidx-lifecycle-runtime-ktx").get())
                    add("implementation", versionCatalog.findLibrary("androidx-navigation-runtime-ktx").get())
                    add("implementation", versionCatalog.findLibrary("androidx-test-espresso-core").get())
                    add("implementation", versionCatalog.findLibrary("androidx-test-core").get())
                    add("implementation", versionCatalog.findLibrary("androidx-test-rules").get())
                    add("implementation", versionCatalog.findLibrary("androidx-test-runner").get())
                    add("implementation", versionCatalog.findLibrary("androidx-test-ext").get())
                    add("implementation", versionCatalog.findLibrary("androidx-work").get())
                    add("implementation", versionCatalog.findLibrary("androidx-work-ktx").get())
            }
        }
    }
}
