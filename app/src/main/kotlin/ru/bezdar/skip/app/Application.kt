package ru.bezdar.skip.app

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import ru.bezdar.skip.app.plugin.configureCors
import ru.bezdar.skip.app.plugin.configureKoin
import ru.bezdar.skip.app.plugin.configureMonitoring
import ru.bezdar.skip.app.plugin.configureRouting
import ru.bezdar.skip.app.plugin.configureSerialization
import ru.bezdar.skip.app.plugin.configureStatusPages

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureCors()
    configureKoin()
    configureMonitoring()
    configureRouting()
    configureSerialization()
    configureStatusPages()
}
