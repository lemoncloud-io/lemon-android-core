package io.lemon.android.buildSystem.extensions

import com.android.build.api.dsl.CommonExtension
import io.lemon.android.buildSystem.Config
import io.lemon.android.buildSystem.Config.JAVA_VERSION


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

        configureFlavor(
            commonExtension = this,
            flavorList = Config.Flavor.FLAVOR_LIST,
        )

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

    }
}