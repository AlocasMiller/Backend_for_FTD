package ru.bezdar.skip.data.database.request.file

import org.jetbrains.exposed.sql.Database
import ru.bezdar.skip.data.database.common.DatabaseDataSourse
import ru.bezdar.skip.data.database.request.entity.RequestEntity
import ru.bezdar.skip.data.database.request.file.entity.FileEntity
import ru.bezdar.skip.data.database.request.file.entity.FileTable
import ru.bezdar.skip.data.database.request.file.entity.toDomain
import ru.bezdar.skip.domain.common.error.RequestNotFound
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.file.FileDbDataSource
import ru.bezdar.skip.domain.request.file.model.File
import ru.bezdar.skip.domain.request.file.model.params.NewFile
import ru.bezdar.skip.domain.request.model.Request

class FileDbDataSourceImpl(override val database: Database) : FileDbDataSource, DatabaseDataSourse {
    override suspend fun addFile(file: NewFile): File = dbQuery {
        val fileEntity = FileEntity.new {
            request = RequestEntity.findById(file.requestId) ?: throw RequestNotFound()
            fileData = file.fileData
        }

        fileEntity.toDomain()
    }

    override suspend fun getFiles(requestId: Id<Request>): List<File> = dbQuery {
        FileEntity.find { FileTable.requestId eq requestId.value }.map(FileEntity::toDomain)
    }
}
