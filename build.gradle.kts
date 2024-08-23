import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.parcelize) apply false
    alias(libs.plugins.ktlint) apply true
}

allprojects {
    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
    }
    ktlint {
        reporters {
            reporter(ReporterType.JSON)
            reporter(ReporterType.CHECKSTYLE)
        }
    }
    tasks.withType<GenerateReportsTask> {
        reportsOutputDirectory.set(
            rootProject.layout.buildDirectory.dir("reports/ktlint/${project.name}")
        )
    }
}

tasks.register<Copy>("assembleAAR") {
    from(
        project.provider {
            subprojects.flatMap { subproject ->
                subproject.layout.buildDirectory.dir("outputs/aar").get().asFile.listFiles()?.toList() ?: emptyList()
            }
        }
    )
    into(rootProject.layout.buildDirectory.dir("outputs/aar"))
}
