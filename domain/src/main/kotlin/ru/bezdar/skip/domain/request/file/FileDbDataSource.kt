package ru.bezdar.skip.domain.request.file

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.file.model.File
import ru.bezdar.skip.domain.request.file.model.params.NewFile
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.user.model.User

interface FileDbDataSource {

    suspend fun addFile(file: NewFile): File

    suspend fun getFiles(requestId: Id<Request>): List<File>
    suspend fun getAllFiles(): List<File>
    suspend fun getFilesByUserId(userId: Id<User>): List<File>
}
