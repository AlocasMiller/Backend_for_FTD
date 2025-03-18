package ru.bezdar.skip.domain.request

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.request.model.params.NewRequest
import ru.bezdar.skip.domain.request.model.params.UpdateRequest
import ru.bezdar.skip.domain.user.model.User

interface RequestDbDataSource {

    suspend fun addRequest(newRequest: NewRequest): Request

    suspend fun getRequests(): List<Request>
    suspend fun getRequestById(id: Id<Request>): Request?
    suspend fun getRequestByUserId(userId: Id<User>): List<Request>

    suspend fun updateRequest(params: UpdateRequest): Request
}
