import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.kapt)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    androidTarget()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.shared.base)
                implementation(projects.shared.network)
                implementation(projects.shared.storage)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.hilt.library)

                configurations["kapt"].dependencies.add(
                    DefaultExternalModuleDependency(
                        "com.google.dagger",
                        "hilt-compiler",
                        libs.versions.dagger.get()
                    )
                )
            }
        }
    }
}

android {
    namespace = "khanesh.shared.feature.home"
}
