package ru.bezdar.skip.domain.request.file

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.file.model.File
import ru.bezdar.skip.domain.request.file.model.params.NewFile
import ru.bezdar.skip.domain.request.model.Request

interface FileDbDataSource {

    suspend fun addFile(file: NewFile): File

    suspend fun getFiles(requestId: Id<Request>): List<File>
}
