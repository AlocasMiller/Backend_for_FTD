package ru.bezdar.skip.app.api.request

import io.ktor.http.ContentDisposition
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.header
import io.ktor.server.response.respondBytes
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject
import ru.bezdar.skip.app.api.common.auth.AuthConstants
import ru.bezdar.skip.app.api.request.controller.RequestController
import ru.bezdar.skip.app.api.request.model.body.CreateRequestBody
import ru.bezdar.skip.app.api.request.model.body.UpdateRequestBody
import ru.bezdar.skip.app.common.ApiVersion
import ru.bezdar.skip.app.common.extentions.getAuthorizedUserId
import ru.bezdar.skip.app.common.extentions.getWithVersion
import ru.bezdar.skip.app.common.extentions.postWithVersion
import ru.bezdar.skip.app.common.extentions.putWithVersion
import ru.bezdar.skip.app.common.extentions.receiveAndValidate
import ru.bezdar.skip.app.common.extentions.respondCreated
import ru.bezdar.skip.app.common.extentions.respondSuccess

fun Route.configureRequestRouting() {
    val controller by inject<RequestController>()

    authenticate(AuthConstants.ACCESS_JWT_NAME) {
        getWithVersion<RequestRoute.Requests>(ApiVersion.V1) {
            val requests = controller.getRequests()
            call.respondSuccess(requests)
        }

        postWithVersion<RequestRoute.Requests>(ApiVersion.V1) {
            val userId = call.getAuthorizedUserId()
            val body = call.receiveAndValidate<CreateRequestBody>()

            val request = controller.createRequest(userId, body)
            call.respondCreated(request)
        }

        postWithVersion<RequestRoute.RequestUpload>(ApiVersion.V1) { params ->
            val responses = controller.uploadFile(params.requestId, call.receiveMultipart())
            call.respondSuccess(responses)
        }

        getWithVersion<RequestRoute.RequestsExport>(ApiVersion.V1) {
            val response = controller.getExports()

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(
                    ContentDisposition.Parameters.FileName,
                    "files.zip"
                ).toString()
            )

            call.respondBytes(
                response.zip,
                contentType = ContentType.Application.Zip,
            )
        }

        getWithVersion<RequestRoute.Request>(ApiVersion.V1) { params ->
            val response = controller.getUserRequests(params.userId)
            call.respondSuccess(response)
        }

        getWithVersion<RequestRoute.RequestExport>(ApiVersion.V1) { params ->
            val response = controller.getUserExport(params.userId)

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(
                    ContentDisposition.Parameters.FileName,
                    "files.zip"
                ).toString()
            )

            call.respondBytes(
                response.zip,
                contentType = ContentType.Application.Zip,
            )
        }

        putWithVersion<RequestRoute.RequestUpdate>(ApiVersion.V1) { params ->
            val moderatorId = call.getAuthorizedUserId()
            val body = call.receiveAndValidate<UpdateRequestBody>()
            
            val request = controller.updateRequest(params.requestId, moderatorId, body)
            call.respondSuccess(request)
        }
    }
}
