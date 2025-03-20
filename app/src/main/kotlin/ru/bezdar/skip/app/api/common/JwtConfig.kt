package ru.bezdar.skip.app.api.common

data class JwtConfig (
    val accessTokenSecret: String,
    val accessTokenValidity: Long,
)
