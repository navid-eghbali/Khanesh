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
    ":core:ui-designsystem",
    ":shared"
)
