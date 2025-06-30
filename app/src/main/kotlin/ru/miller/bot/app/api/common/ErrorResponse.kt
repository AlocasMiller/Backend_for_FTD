package ru.miller.bot.app.api.common

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: String,
    val message: String?,
)
