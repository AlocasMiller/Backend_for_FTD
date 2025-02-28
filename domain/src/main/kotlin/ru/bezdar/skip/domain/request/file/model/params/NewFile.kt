package ru.bezdar.skip.domain.request.file.model.params

import java.util.UUID

data class NewFile(
    val requestId: UUID,
    val fileData: ByteArray,
)
