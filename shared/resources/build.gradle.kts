plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "app.newsly.shared.resources"
    compileSdk = 32
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
}