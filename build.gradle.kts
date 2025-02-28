@file:Suppress("ERROR_SUPPRESSION")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("io.ktor.plugin") version "3.1.0"
    alias(libs.plugins.kotlin.gradle.plugin)
    alias(libs.plugins.dependencyCheck)
}

subprojects {
    group = "ru.bezdar"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}
