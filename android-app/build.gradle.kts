@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "app.khanesh"
    defaultConfig {
        applicationId = "app.khanesh.android"
        versionCode = 1
        versionName = "0.0.1"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(projects.core.base)
    implementation(projects.core.uiDesignsystem)
    implementation(projects.core.uiResources)

    implementation(projects.feature.bookDetails)
    implementation(projects.feature.books)
    implementation(projects.feature.explore)
    implementation(projects.feature.genres)
    implementation(projects.feature.home)
    implementation(projects.feature.library)
    implementation(projects.feature.search)

    implementation(projects.shared.base)
    implementation(projects.shared.coreModel)
    implementation(projects.shared.coreResult)
    implementation(projects.shared.dataGenres)
    implementation(projects.shared.featureHome)
    implementation(projects.shared.network)
    implementation(projects.shared.storage)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.coil)

    implementation(libs.hilt.library)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)
}

kapt {
    correctErrorTypes = true
}
