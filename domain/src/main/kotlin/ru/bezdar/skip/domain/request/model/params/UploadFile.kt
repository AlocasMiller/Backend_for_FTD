package ru.bezdar.skip.domain.request.model.params

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.model.Request

data class UploadFile(
    val requestId: Id<Request>,
    val partData: Map<String, ByteArray>,
)
