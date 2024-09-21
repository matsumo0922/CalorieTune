package primitive

import me.matsumo.calorie.tune.dsl.androidTestImplementation
import me.matsumo.calorie.tune.dsl.commonExt
import me.matsumo.calorie.tune.dsl.debugImplementation
import me.matsumo.calorie.tune.dsl.implementation
import me.matsumo.calorie.tune.dsl.library
import me.matsumo.calorie.tune.dsl.libs
import me.matsumo.calorie.tune.dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

class ComposePlugin : Plugin<Project> {

    private val ComposeExtension.desktop: DesktopExtension
        get() =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("desktop") as DesktopExtension

    private val Project.compose: ComposeExtension
        get() =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("compose") as ComposeExtension

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            commonExt {
                buildFeatures.compose = true
            }

            compose.desktop.run {
                application {
                    mainClass = "MainKt"

                    nativeDistributions {
                        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                        packageName = "me.matsumo.calorie.tune"
                        packageVersion = libs.version("versionName")
                    }
                }
            }

            dependencies {
                val bom = libs.library("compose-bom")

                implementation(project.dependencies.platform(bom))
                implementation(libs.library("compose-ui-tooling-preview"))
                debugImplementation(libs.library("compose-ui-tooling"))
                androidTestImplementation(project.dependencies.platform(bom))
            }
        }
    }
}