package ru.bezdar.skip.app.plugin

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages

@Suppress("LongMethod")
fun Application.configureStatusPages() {
    install(StatusPages) {

    }
}
