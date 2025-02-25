package ru.bezdar.skip.app.api.common

data class JwtConfig (
    val accessTokenSecret: String,
    val refreshTokenSecret: String,
    val accessTokenValidity: Long,
    val refreshTokenValidity: Long,
)
