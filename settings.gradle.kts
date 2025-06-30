rootProject.name = "bot-backend"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

include("app")
include("domain")
include("data")
