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

include(":feature:splash")
include(":feature:news")
include(":feature:editorchoice")
include(":feature:categories")
include(":feature:apps")
include(":feature:main")

include(":core:network")
include(":core:domain")
include(":core:designsystem")
include(":core:data")

include(":shared")
include(":shared:resources")
include(":shared:model")
include(":feature:newsdetail")
