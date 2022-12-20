plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "app.newsly.feature.apps"
    flavorDimensions += "version"
    productFlavors {
        create("mock") {
            dimension = "version"
        }
        create("development") {
            dimension = "version"
        }
        create("production") {
            dimension = "version"
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composecompiler.get()
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(libs.androidx.hilt.navigation.compose)
}