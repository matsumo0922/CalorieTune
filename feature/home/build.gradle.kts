plugins {
    id("matsumo.primitive.android.library")
    id("matsumo.primitive.compose")
    id("matsumo.primitive.kmp")
    id("matsumo.primitive.detekt")
}

android {
    namespace = "me.matsumo.calorie.tune.feature.home"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:ui"))
            implementation(project(":core:model"))
            implementation(project(":core:common"))
        }
    }
}

ksp {
    arg("KOIN_DEFAULT_MODULE", "false")
}
