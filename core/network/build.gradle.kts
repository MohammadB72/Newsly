plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "app.newsly.core.network"
}


dependencies {
    implementation(project(":core:model"))
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gsonConverter)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}