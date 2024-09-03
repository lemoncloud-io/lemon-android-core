package io.lemoncloud.android.buildSystem.extensions

import com.android.build.api.dsl.CommonExtension
import io.lemoncloud.android.buildSystem.Config.JAVA_VERSION


internal fun extensionAndroid(
    commonExtension: CommonExtension<*, *, *, *, *,*>,
) {
    commonExtension.apply {
        defaultConfig {
            buildFeatures {
                buildConfig = true
            }
        }

        compileOptions {
            sourceCompatibility = JAVA_VERSION  // A Java version used in compile
            targetCompatibility = JAVA_VERSION  // The lowest Java version that a android can run
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

    }
}
