plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
    implementation(libs.kover.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.build.konfig.gradlePlugin)
}


gradlePlugin {
    plugins {
        register("AndroidApplication") {
            id = "matsumo.primitive.android.application"
            implementationClass = "primitive.ApplicationPlugin"
        }
        register("AndroidLibrary") {
            id = "matsumo.primitive.android.library"
            implementationClass = "primitive.LibraryPlugin"
        }
        register("ComposePlugin") {
            id = "matsumo.primitive.compose"
            implementationClass = "primitive.ComposePlugin"
        }
        register("DetektPlugin") {
            id = "matsumo.primitive.detekt"
            implementationClass = "primitive.DetektPlugin"
        }
        register("KmpPlugin") {
            id = "matsumo.primitive.kmp"
            implementationClass = "primitive.KmpPlugin"
        }
    }
}