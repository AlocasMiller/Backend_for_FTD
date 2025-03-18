package ru.bezdar.skip.app.api.request.model.response

import kotlinx.serialization.Serializable
import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.api.common.model.toResponse
import ru.bezdar.skip.domain.request.file.model.File

@Serializable
data class FileResponse(
    val id: IdDto,
)

fun File.toResponse() = FileResponse(
    id = id.toResponse(),
)
