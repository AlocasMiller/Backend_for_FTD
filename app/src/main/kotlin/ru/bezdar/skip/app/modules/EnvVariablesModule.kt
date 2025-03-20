package ru.bezdar.skip.app.modules

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bezdar.skip.app.api.common.JwtConfig
import ru.bezdar.skip.data.common.EnvVariablesReader
import ru.bezdar.skip.data.database.common.DatabaseConfig

val envVariablesModule = module {
    singleOf(::createJwtConfig)
    singleOf(::createDatabaseConfig)
}

private fun createJwtConfig(): JwtConfig = JwtConfig(
    accessTokenSecret = EnvVariablesReader.accessTokenSecret,
    accessTokenValidity = EnvVariablesReader.accessTokenValidity,
)

private fun createDatabaseConfig(): DatabaseConfig = DatabaseConfig(
    jdbcUrl = EnvVariablesReader.jdbcURL,
    username = EnvVariablesReader.username,
    password = EnvVariablesReader.password,
    connectionLimit = EnvVariablesReader.connectionLimit,
)
