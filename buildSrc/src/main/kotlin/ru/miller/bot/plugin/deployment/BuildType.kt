package ru.miller.bot.plugin.deployment

enum class BuildType {
    DEV, LIVE
}

fun BuildType.isDev(): Boolean = this == BuildType.DEV
