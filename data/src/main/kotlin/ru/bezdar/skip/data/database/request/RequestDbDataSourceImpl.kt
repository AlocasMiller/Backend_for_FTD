package ru.bezdar.skip.data.database.request

import org.jetbrains.exposed.sql.Database
import ru.bezdar.skip.data.database.common.DatabaseDataSourse
import ru.bezdar.skip.data.database.request.entity.RequestEntity
import ru.bezdar.skip.data.database.request.entity.toDb
import ru.bezdar.skip.data.database.request.entity.toDomain
import ru.bezdar.skip.data.database.user.entity.UserEntity
import ru.bezdar.skip.domain.common.error.RequestNotFound
import ru.bezdar.skip.domain.common.error.UserNotFound
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.RequestDbDataSource
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.request.model.params.NewRequest
import ru.bezdar.skip.domain.request.model.params.UpdateRequest
import java.time.Instant

class RequestDbDataSourceImpl(override val database: Database) : RequestDbDataSource, DatabaseDataSourse {

    override suspend fun addRequest(request: NewRequest): Request = dbQuery {
        val requestEntity = RequestEntity.new {
            creator = UserEntity.findById(request.creatorId) ?: throw UserNotFound()
            createdAt = Instant.now()
            dateStart = request.dateStart
            dateEnd = request.dateEnd
            comment = request.comment
            status = request.status.toDb()
            reason = request.reason.toDb()
            fileInDean = request.fileInDean
        }

        requestEntity.toDomain()
    }

    override suspend fun getRequests(): List<Request> = dbQuery {
        RequestEntity.all().map { it.toDomain() }
    }

    override suspend fun getRequestById(id: Id<Request>): Request? = dbQuery {
        RequestEntity.findById(id.value)?.toDomain()
    }

    override suspend fun updateRequest(params: UpdateRequest): Request = dbQuery {
        RequestEntity.findById(params.id.value)?.apply {
            this.creator = UserEntity.findById(params.creatorId.value) ?: throw UserNotFound()
            this.moderator = UserEntity.findById(params.moderatorId.value) ?: throw UserNotFound()
            this.dateStart = params.dateStart
            this.dateEnd = params.dateEnd
            this.comment = params.comment
            this.status = params.status.toDb()
            this.reason = params.reason.toDb()
            this.fileInDean = params.fileInDean
        }?.toDomain() ?: throw RequestNotFound()
    }
}
