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
        maven("https://jitpack.io")
    }
}
rootProject.name = "Kalorické Tabulky Lite"

include(":app")
include(":core-network")
include(":core-testing")
include(":core-ui")
include(":feature-foodexplorer")
include(":test-app")
