plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
}

allprojects {

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            // Treat all Kotlin warnings as errors
            allWarningsAsErrors = false

            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs += listOf(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlin.Experimental",
            )

            if (project.hasProperty("newsly.enableComposeCompilerReports")) {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                            project.buildDir.absolutePath + "/compose_metrics",
                )
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                            project.buildDir.absolutePath + "/compose_metrics",
                )
            }

            // Set JVM target to 11
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    plugins.withId(rootProject.libs.plugins.hilt.get().pluginId) {
        extensions.getByType<dagger.hilt.android.plugin.HiltExtension>().enableAggregatingTask =
            true
    }
    plugins.withId(rootProject.libs.plugins.kotlin.kapt.get().pluginId) {
        extensions.getByType<org.jetbrains.kotlin.gradle.plugin.KaptExtension>().correctErrorTypes =
            true
    }
    plugins.withType<com.android.build.gradle.BasePlugin>().configureEach {
        extensions.configure<com.android.build.gradle.BaseExtension> {
            compileSdkVersion(libs.versions.compileSdk.get().toInt())
            defaultConfig {
                minSdk = libs.versions.minSdk.get().toInt()
                targetSdk = libs.versions.targetSdk.get().toInt()
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }
    }
}