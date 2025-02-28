package ru.bezdar.skip.domain.auth.model

import java.time.Instant

data class Token(
    val token: String,
    val expireAt: Instant,
)
