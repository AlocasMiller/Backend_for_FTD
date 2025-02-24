package ru.bezdar.skip.data.common

object EnvVariablesReader {

    private const val KEY_DATABASE_JDBC_URL = "DATABASE_JDBC_URL"
    private const val KEY_DATABASE_USERNAME = "DATABASE_USERNAME"
    private const val KEY_DATABASE_PASSWORD = "DATABASE_PASSWORD"
    private const val KEY_DATABASE_CONNECTION_LIMIT = "DATABASE_CONNECTION_LIMIT"

    val jdbcURL: String get() = System.getenv(KEY_DATABASE_JDBC_URL)
    val username: String get() = System.getenv(KEY_DATABASE_USERNAME)
    val password: String get() = System.getenv(KEY_DATABASE_PASSWORD)
    val connectionLimit get() = System.getenv(KEY_DATABASE_CONNECTION_LIMIT).toInt()

    private const val KEY_ACCESS_TOKEN_SECRET = "ACCESS_TOKEN_SECRET"
    private const val KEY_REFRESH_TOKEN_SECRET = "REFRESH_TOKEN_SECRET"
    private const val KEY_ACCESS_TOKEN_VALIDITY = "ACCESS_TOKEN_VALIDITY"
    private const val KEY_REFRESH_TOKEN_VALIDITY = "REFRESH_TOKEN_VALIDITY"

    val accessTokenSecret: String get() = System.getenv(KEY_ACCESS_TOKEN_SECRET)
    val refreshTokenSecret: String get() = System.getenv(KEY_REFRESH_TOKEN_SECRET)
    val accessTokenValidity: Long get() = System.getenv(KEY_ACCESS_TOKEN_VALIDITY).toLong()
    val refreshTokenValidity: Long get() = System.getenv(KEY_REFRESH_TOKEN_VALIDITY).toLong()
}
