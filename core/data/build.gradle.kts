plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "app.newsly.core.data"
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
    implementation(project(":shared:model"))
    implementation(project(":core:network"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}