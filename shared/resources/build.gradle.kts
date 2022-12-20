plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "app.newsly.shared.resources"

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
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
}