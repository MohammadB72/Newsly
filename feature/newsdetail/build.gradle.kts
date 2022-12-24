plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "app.newsly.feature.newsdetail"

    flavorDimensions += flavor.dimension.FlavorDimension.MODE.value
    productFlavors {
        create(flavor.MockFlavor.name) {
            dimension = flavor.dimension.FlavorDimension.MODE.value
        }
        create(flavor.DevelopmentFlavor.name) {
            dimension = flavor.dimension.FlavorDimension.MODE.value
        }
        create(flavor.ProductionFlavor.name) {
            dimension = flavor.dimension.FlavorDimension.MODE.value
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
    implementation(project(":core:domain"))
    implementation(project(":shared:model"))
    implementation(project(":shared:resources"))

    implementation(libs.androidx.lifecycle.runtimeCompose)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}