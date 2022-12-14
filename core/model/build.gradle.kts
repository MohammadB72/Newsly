plugins {
    id("org.jetbrains.kotlin.jvm")
    id("kotlinx-serialization")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
