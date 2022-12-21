plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "app.newsly.core.network"
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
    implementation(project(":shared:resources"))

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gsonConverter)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}