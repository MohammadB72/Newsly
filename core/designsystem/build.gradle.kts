plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "app.newsly.core.designsystem"
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
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui.ui)
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.test.manifest)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.material3)
    api(libs.google.android.material)
    api(libs.androidx.compose.material.iconsExtended)
}