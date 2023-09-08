plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "khanesh.core.ui.designsystem"

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    api(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.foundation.foundation)
    implementation(libs.androidx.compose.material3.material3)
}
