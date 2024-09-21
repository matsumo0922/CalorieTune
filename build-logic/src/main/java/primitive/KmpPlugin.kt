@file:OptIn(ExperimentalWasmDsl::class)

package primitive

import me.matsumo.calorie.tune.dsl.android
import me.matsumo.calorie.tune.dsl.bundle
import me.matsumo.calorie.tune.dsl.kotlin
import me.matsumo.calorie.tune.dsl.library
import me.matsumo.calorie.tune.dsl.libs
import me.matsumo.calorie.tune.dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.impldep.org.yaml.snakeyaml.composer.ComposerException
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

class KmpPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")

            android {
                sourceSets {
                    getByName("main") {
                        manifest.srcFile("src/androidMain/AndroidManifest.xml")
                        res.srcDirs("src/androidMain/res")
                    }
                }
            }

            kotlin {
                configureKmpAndroid()
                configureKmpIos()
                configureDesktop()

                sourceSets.commonMain {
                    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")

                    dependencies {
                        implementation(project.dependencies.platform(libs.library("koin-bom")))
                        implementation(libs.bundle("koin"))
                    }
                }
            }

            dependencies {
                add("kspCommonMainMetadata", libs.library("koin-ksp-compiler"))
            }

            // WORKAROUND FOR KOIN KSP: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
            tasks.withType<KotlinCompilationTask<*>>().configureEach {
                if (name != "kspCommonMainKotlinMetadata") {
                    dependsOn("kspCommonMainKotlinMetadata")
                }
            }
        }
    }
}

private fun KotlinMultiplatformExtension.configureKmpAndroid() {
    androidTarget()
    applyDefaultHierarchyTemplate()
}

private fun KotlinMultiplatformExtension.configureKmpIos() {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.apply {
            binaries.framework {
                baseName = "ComposeApp"
                isStatic = true
            }
        }
    }
}

private fun KotlinMultiplatformExtension.configureDesktop() {
    jvm()
}
