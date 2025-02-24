package ru.bezdar.skip.data.database.common

data class DatabaseConfig(
    val jdbcUrl: String,
    val username: String,
    val password: String,
    val connectionLimit: Int,
)
