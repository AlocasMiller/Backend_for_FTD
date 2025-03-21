package ru.bezdar.skip.app.api.common

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: String,
    val message: String?,
)
