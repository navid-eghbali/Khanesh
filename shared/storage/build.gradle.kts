@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.sqldelight)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    android()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.shared.base)

                implementation(libs.sqldelight.coroutines)
                implementation(libs.sqldelight.paging)
                implementation(libs.sqldelight.primitive)
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
                implementation(libs.sqldelight.android)

                configurations["kapt"].dependencies.add(
                    org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
                        "com.google.dagger",
                        "hilt-compiler",
                        libs.versions.dagger.get()
                    )
                )
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.sqldelight.native)
            }
        }
    }
}

sqldelight {
    databases {
        create("Storage") {
            packageName.set("khanesh.shared.storage")
        }
    }
}

android {
    namespace = "khanesh.shared.storage"
}
