pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Khanesh"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    ":android-app",
    ":core:base",
    ":core:ui-designsystem",
    ":core:ui-resources",
    ":feature:book-details",
    ":feature:explore",
    ":feature:home",
    ":feature:library",
    ":shared:core-di",
    ":shared:core-model",
    ":shared:core-result",
    ":shared:network",
)
