package io.lemoncloud.android.buildSystem

import io.lemoncloud.android.buildSystem.extensions.type.FlavorType
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

internal object Config {

    const val COMPILE_SDK = 34
    const val MIN_SDK = 26
    const val TARGET_SDK = 34
    const val VERSION_NAME = "0.0.1"
    val VERSION_CODE: Int =
        ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH")).toInt()

    // You must change app package path and it.
    const val APPLICATION_ID = "io.lemoncloud.core"

    val JAVA_VERSION = JavaVersion.VERSION_17
    val JVM_TARGET = JvmTarget.JVM_17

    //Build Type (default, debug and release)
    internal object BuildType {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    /**
     * [Flavor]
     *
     * Build flavor config.
     *
     * Change value field in ResourceType.
     */
    internal object Flavor {
        val FLAVOR_LIST: List<FlavorType> = listOf(
            FlavorType(
                name = "dev",
                suffix = ".dev",
            ),
            FlavorType(
                name = "staging",
                suffix = ".staging",
            ),
            FlavorType(
                name = "live",
            )
        )
    }
}
