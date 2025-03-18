package ru.bezdar.skip.domain.request.file.model.params

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.model.Request

data class NewFile(
    val requestId: Id<Request>,
    val fileName: String,
    val fileData: ByteArray,
)
