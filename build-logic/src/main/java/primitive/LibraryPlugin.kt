package primitive

import me.matsumo.translator.configureKsp
import me.matsumo.translator.dsl.androidLibrary
import me.matsumo.translator.dsl.libs
import me.matsumo.translator.dsl.setupAndroid
import me.matsumo.translator.dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlinx-serialization")
                apply("com.google.devtools.ksp")
            }

            androidLibrary {
                setupAndroid()
                configureKsp()

                compileSdk = libs.version("compileSdk").toInt()
                defaultConfig.targetSdk = libs.version("targetSdk").toInt()
                buildFeatures.viewBinding = true
            }
        }
    }
}