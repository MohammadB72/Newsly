plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "app.newsly.shared.resources"

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
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
}