package ru.bezdar.skip.app.plugin

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.resources.Resources
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import ru.bezdar.skip.app.api.group.configureGroupRouting
import ru.bezdar.skip.app.api.request.configureRequestRouting
import ru.bezdar.skip.app.api.user.configureUserRouting

fun Application.configureRouting() {
    install(Resources) {
        serializersModule = SerializationConfig.serializersModule
    }

    routing {
        route("api") {
            configureRequestRouting()
            configureGroupRouting()
            configureUserRouting()
        }
    }
}
