package ru.miller.bot.app

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import ru.miller.bot.app.plugin.configureCors
import ru.miller.bot.app.plugin.configureFlyway
import ru.miller.bot.app.plugin.configureKoin
import ru.miller.bot.app.plugin.configureMonitoring
import ru.miller.bot.app.plugin.configureRouting
import ru.miller.bot.app.plugin.configureSerialization
import ru.miller.bot.app.plugin.configureStatusPages

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureMonitoring()
    configureCors()
    configureKoin()
    configureFlyway()
    configureSerialization()
    configureStatusPages()
    configureRouting()
}
