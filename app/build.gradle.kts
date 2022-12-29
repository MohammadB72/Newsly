import flavor.DevelopmentFlavor
import flavor.MockFlavor
import flavor.ProductionFlavor
import flavor.dimension.FlavorDimension

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "app.newsly"
    compileSdk = 33

    defaultConfig {
        applicationId = "app.newsly"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            versionNameSuffix = "-debug"
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += FlavorDimension.MODE.value
    productFlavors {
        create(MockFlavor.name) {
            dimension = FlavorDimension.MODE.value
            versionNameSuffix = MockFlavor.versionNameSuffix
            applicationIdSuffix = MockFlavor.applicationIdSuffix
        }
        create(DevelopmentFlavor.name) {
            dimension = FlavorDimension.MODE.value
            versionNameSuffix = DevelopmentFlavor.versionNameSuffix
            applicationIdSuffix = DevelopmentFlavor.applicationIdSuffix
        }
        create(ProductionFlavor.name) {
            dimension = FlavorDimension.MODE.value
            versionNameSuffix = ProductionFlavor.versionNameSuffix
            applicationIdSuffix = ProductionFlavor.applicationIdSuffix
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composecompiler.get()
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":feature:main"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:newsdetail"))
    implementation(project(":feature:subcategories"))

    implementation(project(":core:designsystem"))

    implementation(project(":shared:model"))
    implementation(project(":shared:resources"))


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}