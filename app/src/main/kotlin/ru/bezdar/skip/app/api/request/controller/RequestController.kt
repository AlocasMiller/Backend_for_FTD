package ru.bezdar.skip.app.api.request.controller

import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.api.common.model.toDomain
import ru.bezdar.skip.app.api.request.model.body.CreateRequestBody
import ru.bezdar.skip.app.api.request.model.body.toDomain
import ru.bezdar.skip.app.api.request.model.response.RequestResponse
import ru.bezdar.skip.app.api.request.model.response.toResponse
import ru.bezdar.skip.domain.request.usecase.CreateRequestUseCase
import ru.bezdar.skip.domain.request.usecase.GetRequestsUseCase
import ru.bezdar.skip.domain.user.model.User

class RequestController (
    private val createRequestsUseCase: CreateRequestUseCase,
    private val getRequestsUseCase: GetRequestsUseCase,
) {

    suspend fun createRequest(userId: IdDto, body: CreateRequestBody): RequestResponse {
        val param = body.toDomain(userId)
        return createRequestsUseCase(param).getOrThrow().toResponse()
    }

    suspend fun getRequests(userId: IdDto): List<RequestResponse> {
        return getRequestsUseCase(userId.toDomain<User>()).getOrThrow().map { it.toResponse() }
    }
}
