import com.vanniktech.maven.publish.SonatypeHost
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
    alias(libs.plugins.vanniktech) apply true
}

subprojects {
    apply(plugin = "com.vanniktech.maven.publish")

    group = "io.lemoncloud"
    version = "0.0.10"

    mavenPublishing {
        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

        signAllPublications()
        pom {
            name.set("Android Lemon Core")
            description.set("This is Android utilities library for Lemon Cloud.")
            url.set("https://github.com/lemoncloud-io/lemon-android-core")

            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }

            developers {
                developer {
                    id.set("lemon")
                    name.set("lemon")
                    email.set("developer@lemoncloud.io")
                }
            }
            scm {
                url.set("https://github.com/lemoncloud-io/lemon-android-core") // GitHub 호스팅 서비스의 리포지토리 URL
            }
        }
    }
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
