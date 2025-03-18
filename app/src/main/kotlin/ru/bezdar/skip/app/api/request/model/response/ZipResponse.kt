package ru.bezdar.skip.app.api.request.model.response

import kotlinx.serialization.Serializable
import ru.bezdar.skip.domain.request.file.common.model.Zip

@Serializable
data class ZipResponse(
    val zip: ByteArray,
)

fun Zip.toResponse() = ZipResponse(
    zip = zip,
)
