package io.lemon.android.buildSystem.plugin

import io.lemon.android.buildSystem.util.Extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DataStorePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", versionCatalog.findLibrary("androidx-datastore-preferences").get())
                add("implementation", versionCatalog.findLibrary("androidx-datastore-preferences-core").get())
                add("implementation", versionCatalog.findLibrary("androidx-datastore").get())
                add("implementation", versionCatalog.findLibrary("androidx-datastore-core").get())
               }
        }
    }
}
