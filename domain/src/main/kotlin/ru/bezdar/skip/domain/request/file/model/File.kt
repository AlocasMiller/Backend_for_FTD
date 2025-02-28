package ru.bezdar.skip.domain.request.file.model

import ru.bezdar.skip.domain.common.model.Id

data class File(
    val id: Id<File>,
    val fileData: ByteArray,
)
