package io.lemoncloud.android.buildSystem.plugin

import com.android.build.api.dsl.ApplicationExtension
import io.lemoncloud.android.buildSystem.extensions.extensionAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<ApplicationExtension> {
                extensionAndroidCompose(this)
            }
        }
    }
}
