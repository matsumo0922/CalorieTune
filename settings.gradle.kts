@file:Suppress("UnstableApiUsage")

rootProject.name = "ai-translator"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

include(":composeApp")
include(":core:ui")
include(":core:model")
include(":core:common")