package ru.bezdar.skip.app.plugin

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import org.koin.ktor.ext.inject
import ru.bezdar.skip.data.database.common.DatabaseProvider

fun Application.configureFlyway() {
    val hikariDataSource by inject<HikariDataSource>()
    DatabaseProvider.runMigration(hikariDataSource)
}
