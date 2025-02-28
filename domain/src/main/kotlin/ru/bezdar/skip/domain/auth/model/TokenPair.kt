package ru.bezdar.skip.domain.auth.model

data class TokenPair(
    val accessToken: Token,
    val refreshToken: Token,
)
