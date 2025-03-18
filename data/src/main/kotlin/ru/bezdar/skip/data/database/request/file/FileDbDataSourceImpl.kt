package ru.bezdar.skip.data.database.request.file

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import ru.bezdar.skip.data.database.common.DatabaseDataSourse
import ru.bezdar.skip.data.database.request.entity.RequestEntity
import ru.bezdar.skip.data.database.request.entity.RequestStatusDb
import ru.bezdar.skip.data.database.request.entity.RequestTable
import ru.bezdar.skip.data.database.request.file.entity.FileEntity
import ru.bezdar.skip.data.database.request.file.entity.FileTable
import ru.bezdar.skip.data.database.request.file.entity.toDomain
import ru.bezdar.skip.domain.common.error.RequestNotFound
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.file.FileDbDataSource
import ru.bezdar.skip.domain.request.file.model.File
import ru.bezdar.skip.domain.request.file.model.params.NewFile
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.user.model.User

class FileDbDataSourceImpl(override val database: Database) : FileDbDataSource, DatabaseDataSourse {
    override suspend fun addFile(file: NewFile): File = dbQuery {
        val fileEntity = FileEntity.new {
            request = RequestEntity.findById(file.requestId.value) ?: throw RequestNotFound()
            fileName = file.fileName
            fileData = file.fileData
        }

        fileEntity.toDomain()
    }

    override suspend fun getFiles(requestId: Id<Request>): List<File> = dbQuery {
        FileEntity.find { FileTable.requestId eq requestId.value }.map(FileEntity::toDomain)
    }

    override suspend fun getAllFiles(): List<File> = dbQuery {
        val requests = RequestEntity.find { RequestTable.status eq RequestStatusDb.APPROVED }
        FileEntity.find { FileTable.requestId inList requests.map { it.id } }.map(FileEntity::toDomain)
    }

    override suspend fun getFilesByUserId(userId: Id<User>): List<File> = dbQuery {
        val requests = RequestEntity.find {
            RequestTable.creatorId eq userId.value and (RequestTable.status eq RequestStatusDb.APPROVED)
        }
        FileEntity.find { FileTable.requestId inList requests.map { it.id } }.map(FileEntity::toDomain)
    }
}
