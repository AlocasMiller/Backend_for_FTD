package ru.miller.bot.app.plugin

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import org.koin.ktor.ext.inject
import ru.miller.bot.data.database.common.DatabaseProvider

fun Application.configureFlyway() {
    val hikariDataSource by inject<HikariDataSource>()
    DatabaseProvider.runMigration(hikariDataSource)
}
