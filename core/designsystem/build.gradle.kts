plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "app.newsly.core.designsystem"

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
    implementation(project(":shared:model"))
    implementation(project(":shared:resources"))

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui.ui)
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.test.manifest)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.material3)
    api(libs.google.android.material)
    api(libs.androidx.compose.material.iconsExtended)

    api(libs.coil)
    api(libs.coil.compose)
    api(libs.coil.svg)
}