package ru.bezdar.skip.app

import io.ktor.server.application.Application
import ru.bezdar.skip.app.plugin.configureAuth
import ru.bezdar.skip.app.plugin.configureCors
import ru.bezdar.skip.app.plugin.configureFlyway
import ru.bezdar.skip.app.plugin.configureKoin
import ru.bezdar.skip.app.plugin.configureMonitoring
import ru.bezdar.skip.app.plugin.configureRouting
import ru.bezdar.skip.app.plugin.configureSerialization
import ru.bezdar.skip.app.plugin.configureStatusPages

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureMonitoring()
    configureCors()
    configureKoin()
    configureFlyway()
    configureSerialization()
    configureStatusPages()
    configureAuth()
    configureRouting()
}
