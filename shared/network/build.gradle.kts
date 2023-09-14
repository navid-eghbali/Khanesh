import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
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
                implementation(projects.shared.coreDi)
                api(projects.shared.coreModel)
                api(projects.shared.coreResult)

                implementation(libs.kotlinx.serialization.json)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.serialization.kotlinx.json)
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
                implementation(libs.ktor.client.android)
                implementation(libs.ktor.client.core.jvm)
                implementation(libs.ktor.client.okhttp)
                implementation(libs.ktor.serialization.kotlinx.json.jvm)

                configurations["kapt"].dependencies.add(
                    DefaultExternalModuleDependency(
                        "com.google.dagger",
                        "hilt-compiler",
                        libs.versions.dagger.get()
                    )
                )
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

android {
    namespace = "khanesh.shared.network"
}
