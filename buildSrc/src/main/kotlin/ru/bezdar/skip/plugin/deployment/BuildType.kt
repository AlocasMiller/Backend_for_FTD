package ru.bezdar.skip.plugin.deployment

enum class BuildType {
    DEV, LIVE
}

fun BuildType.isDev(): Boolean = this == BuildType.DEV
