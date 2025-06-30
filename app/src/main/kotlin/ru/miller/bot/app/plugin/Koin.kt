package ru.miller.bot.app.plugin

import io.ktor.server.application.Application
import org.koin.ktor.plugin.koin
import ru.miller.bot.app.modules.KoinModules

fun Application.configureKoin() {
    koin {
        modules(KoinModules.all)
    }
}
