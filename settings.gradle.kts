rootProject.name = "skip-backend"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

include("app")
include("domain")
include("data")
