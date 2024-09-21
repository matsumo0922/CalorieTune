
import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.codingfeline.buildkonfig.gradle.TargetConfigDsl
import java.util.Properties

plugins {
    id("matsumo.primitive.android.application")
    id("matsumo.primitive.compose")
    id("matsumo.primitive.kmp")
    id("matsumo.primitive.detekt")
}

android {
    namespace = "me.matsumo.calorie.tune"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:home"))

            implementation(project(":core:common"))
            implementation(project(":core:model"))
            implementation(project(":core:ui"))
        }

        androidMain.dependencies {
            implementation(libs.bundles.ui.implementations)
            implementation(libs.koin.android)
        }
    }
}

buildkonfig {
    val file = project.rootDir.resolve("local.properties")
    val localProperties = Properties().apply {
        if (file.exists()) load(file.inputStream())
    }

    packageName = "me.matsumo.calorie.tune"

    defaultConfigs {
        putBuildConfig(localProperties, "OPEN_AI_API_KEY")
    }
}

fun TargetConfigDsl.putBuildConfig(
    localProperties: Properties,
    key: String,
    value: String? = null,
    defaultValue: String = "",
) {
    val property = localProperties.getProperty(key)
    val env = System.getenv(key)

    buildConfigField(FieldSpec.Type.STRING, key, (value ?: property ?: env ?: defaultValue).replace("\"", ""))
}

fun Any.toStringLiteral(): String {
    val value = toString()
    return if (value.firstOrNull() == '\"' && value.lastOrNull() == '\"') value else "\"$value\""
}