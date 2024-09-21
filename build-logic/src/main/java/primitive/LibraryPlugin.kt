package primitive

import me.matsumo.calorie.tune.configureKsp
import me.matsumo.calorie.tune.dsl.androidLibrary
import me.matsumo.calorie.tune.dsl.libs
import me.matsumo.calorie.tune.dsl.setupAndroid
import me.matsumo.calorie.tune.dsl.version
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