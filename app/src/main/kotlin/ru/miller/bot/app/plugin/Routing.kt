package ru.miller.bot.app.plugin

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.resources.Resources
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import ru.miller.bot.app.api.lesson.configureLessonRouting
import ru.miller.bot.app.api.user.configureUserRouting

fun Application.configureRouting() {
    install(Resources) {
        serializersModule = SerializationConfig.serializersModule
    }

    routing {
        route("api") {
            configureUserRouting()
            configureLessonRouting()
            swaggerUI(path = "swagger", swaggerFile = "app/src/main/resources/bot-api.yaml") {
                version = "4.15.5"
            }
        }
    }
}
