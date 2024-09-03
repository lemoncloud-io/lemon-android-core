package io.lemoncloud.android.buildSystem.util

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType


internal object Extensions {

    val Project.applicationExtension: ApplicationExtension
        get():ApplicationExtension = extensions.getByType<ApplicationExtension>()

    val Project.libraryExtension: LibraryExtension
        get():LibraryExtension = extensions.getByType<LibraryExtension>()

    val Project.versionCatalog: VersionCatalog
        get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

}
