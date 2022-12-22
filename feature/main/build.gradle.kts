plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "app.newsly.feature.main"

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
    implementation(project(":feature:news"))
    implementation(project(":feature:editorchoice"))
    implementation(project(":feature:categories"))
    implementation(project(":feature:apps"))

    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))

    implementation(project(":shared:resources"))

    implementation(libs.androidx.hilt.navigation.compose)
}