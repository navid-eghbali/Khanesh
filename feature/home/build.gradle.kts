@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "khanesh.feature.home"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(projects.core.uiDesignsystem)
    implementation(projects.core.uiResources)
    implementation(projects.shared.featureHome)
    implementation(projects.shared.network)
    implementation(projects.shared.storage)

    implementation(libs.accompanist.placeholder)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil)
    implementation(libs.hilt.library)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)
}
