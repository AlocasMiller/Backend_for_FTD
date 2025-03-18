package ru.bezdar.skip.app.api.request.controller

import io.ktor.http.content.MultiPartData
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.api.common.model.toDomain
import ru.bezdar.skip.app.api.request.model.body.CreateRequestBody
import ru.bezdar.skip.app.api.request.model.body.UpdateRequestBody
import ru.bezdar.skip.app.api.request.model.body.toDomain
import ru.bezdar.skip.app.api.request.model.response.FileResponse
import ru.bezdar.skip.app.api.request.model.response.RequestResponse
import ru.bezdar.skip.app.api.request.model.response.ZipResponse
import ru.bezdar.skip.app.api.request.model.response.toResponse
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.request.model.params.UpdateRequest
import ru.bezdar.skip.domain.request.model.params.UploadFile
import ru.bezdar.skip.domain.request.usecase.CreateRequestUseCase
import ru.bezdar.skip.domain.request.usecase.GetUserRequestUseCase
import ru.bezdar.skip.domain.request.usecase.GetExportUseCase
import ru.bezdar.skip.domain.request.usecase.GetRequestsUseCase
import ru.bezdar.skip.domain.request.usecase.GetUserExportUseCase
import ru.bezdar.skip.domain.request.usecase.UpdateRequestUseCase
import ru.bezdar.skip.domain.request.usecase.UploadFileUseCase
import ru.bezdar.skip.domain.user.model.User
import kotlin.getOrThrow

@Suppress("detekt:LongParameterList")
class RequestController (
    private val createRequestUseCase: CreateRequestUseCase,
    private val getExportUseCase: GetExportUseCase,
    private val getRequestsUseCase: GetRequestsUseCase,
    private val getUserExportUseCase: GetUserExportUseCase,
    private val getUserRequestUseCase: GetUserRequestUseCase,
    private val updateRequestUseCase: UpdateRequestUseCase,
    private val uploadFileUseCase: UploadFileUseCase,
    ) {

    suspend fun createRequest(userId: IdDto, body: CreateRequestBody): RequestResponse {
        val param = body.toDomain(userId)
        return createRequestUseCase(param).getOrThrow().toResponse()
    }

    suspend fun uploadFile(requestId: IdDto, multiPartData: MultiPartData): List<FileResponse> {
        val list = mutableMapOf<String, ByteArray>()
        multiPartData.forEachPart { part ->
            when (part) {
                is PartData.FileItem -> {
                    list.put(
                        part.originalFileName!!.substringAfterLast(".", "unknown"),
                        part.streamProvider().readBytes()
                    )
                }
                else -> Unit
            }
            part.dispose()
        }
        val params = UploadFile(requestId.toDomain<Request>(), list)
        return uploadFileUseCase(params).getOrThrow().map { it.toResponse() }
    }

    suspend fun getRequests(): List<RequestResponse> {
        return getRequestsUseCase(Unit).getOrThrow().map { it.toResponse() }
    }

    suspend fun getExports(): ZipResponse {
         return getExportUseCase(Unit).getOrThrow().toResponse()
    }

    suspend fun getUserRequests(userId: IdDto): List<RequestResponse> {
        return getUserRequestUseCase(userId.toDomain<User>()).getOrThrow().map { it.toResponse() }
    }

    suspend fun getUserExport(userId: IdDto): ZipResponse {
        return getUserExportUseCase(userId.toDomain<User>()).getOrThrow().toResponse()
    }

    suspend fun updateRequest(requestId: IdDto, moderatorId: IdDto, body: UpdateRequestBody): RequestResponse {
        val params = UpdateRequest(
            id = requestId.toDomain(),
            creatorId = body.creatorId.toDomain(),
            moderatorId = moderatorId.toDomain(),
            dateStart = body.dateStart,
            dateEnd = body.dateEnd,
            comment = body.comment,
            status = body.status,
            reason = body.reason,
            fileInDean = body.fileInDean,
        )
        return updateRequestUseCase(params).getOrThrow().toResponse()
    }
}
