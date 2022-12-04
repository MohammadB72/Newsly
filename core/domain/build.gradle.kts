plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "app.newsly.core.domain"
}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}