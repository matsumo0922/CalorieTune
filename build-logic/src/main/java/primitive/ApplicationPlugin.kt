package primitive

import me.matsumo.calorie.tune.configureKsp
import me.matsumo.calorie.tune.dsl.androidApplication
import me.matsumo.calorie.tune.dsl.libs
import me.matsumo.calorie.tune.dsl.setupAndroid
import me.matsumo.calorie.tune.dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project

class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("kotlinx-serialization")
                apply("com.google.devtools.ksp")
                apply("com.codingfeline.buildkonfig")
            }

            androidApplication {
                setupAndroid()
                configureKsp()

                compileSdk = libs.version("compileSdk").toInt()
                defaultConfig.targetSdk = libs.version("targetSdk").toInt()
                buildFeatures.viewBinding = true

                defaultConfig {
                    applicationId = "jp.co.yumemi.droidtraining"

                    versionName = libs.version("versionName")
                    versionCode = libs.version("versionCode").toInt()
                }
            }
        }
    }
}