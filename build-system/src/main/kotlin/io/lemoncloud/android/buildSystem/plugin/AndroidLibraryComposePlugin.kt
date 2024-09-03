package io.lemoncloud.android.buildSystem.plugin

import com.android.build.gradle.LibraryExtension
import io.lemoncloud.android.buildSystem.extensions.extensionAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<LibraryExtension> {
                extensionAndroidCompose(this)
            }
        }
    }
}
