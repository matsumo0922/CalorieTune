plugins {
    id("matsumo.primitive.android.library")
    id("matsumo.primitive.kmp")
    id("matsumo.primitive.detekt")
}

android {
    namespace = "me.matsumo.calorie.tune.core.common"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.bundles.infra.api)
        }
    }
}
