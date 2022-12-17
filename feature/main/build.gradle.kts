plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "app.newsly.feature.main"
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