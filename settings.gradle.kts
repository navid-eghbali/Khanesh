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
    ":feature:genres",
    ":feature:home",
    ":feature:library",
    ":feature:search",
    ":shared:base",
    ":shared:core-model",
    ":shared:core-result",
    ":shared:data-books",
    ":shared:data-genres",
    ":shared:feature-home",
    ":shared:network",
    ":shared:storage",
)
