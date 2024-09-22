plugins {
    id("matsumo.primitive.android.library")
    id("matsumo.primitive.kmp")
    id("matsumo.primitive.detekt")
}

android {
    namespace = "me.matsumo.calorie.tune.core.model"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:common"))
        }
    }
}
