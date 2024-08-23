package io.lemon.android.buildSystem.plugin

import com.android.build.api.dsl.ApplicationExtension
import io.lemon.android.buildSystem.Config.COMPILE_SDK
import io.lemon.android.buildSystem.Config.MIN_SDK
import io.lemon.android.buildSystem.Config.TARGET_SDK
import io.lemon.android.buildSystem.Config.VERSION_CODE
import io.lemon.android.buildSystem.Config.VERSION_NAME
import io.lemon.android.buildSystem.Config.APPLICATION_ID
import io.lemon.android.buildSystem.Config.BuildType.DEBUG
import io.lemon.android.buildSystem.Config.BuildType.RELEASE
import io.lemon.android.buildSystem.extensions.extensionAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                extensionAndroid(this)


                /**
                 *  Write your keystore store configs.
                 *
                 *  signingConfigs {
                 *     create(RELEASE) {
                 *         storeFile = file("../your_file_path")
                 *         storePassword = ""
                 *         keyAlias = ""
                 *         keyPassword = ""
                 *     }
                 * }
                 */


                defaultConfig {
                    applicationId = APPLICATION_ID
                    minSdk = MIN_SDK
                    targetSdk = TARGET_SDK
                    compileSdk = COMPILE_SDK
                    versionCode = VERSION_CODE
                    versionName = VERSION_NAME
                    vectorDrawables.useSupportLibrary = true

                    buildTypes {
                        getByName(DEBUG) {
                            isDebuggable = true
                            isMinifyEnabled = false
                            isShrinkResources = false
                        }

                        getByName(RELEASE) {

                            /**
                             * If you have key store file use below line
                             *
                             * signingConfig = signingConfigs.getByName(RELEASE)
                             */
                            isDebuggable = false
                            isMinifyEnabled = true
                            isShrinkResources = true
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                        }
                    }
                }
            }
        }
    }
}
