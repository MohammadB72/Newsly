pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Newsly"
include(":app")
include(":core")
include(":core:network")
include(":core:domain")
include(":feature")
include(":feature:splashscreen")
include(":feature:news")
include(":feature:editorchoice")
include(":feature:categories")
include(":feature:apps")
include(":core:designsystem")
include(":feature:main")
