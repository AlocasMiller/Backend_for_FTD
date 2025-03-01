package ru.bezdar.skip.app.api.request

import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject
import ru.bezdar.skip.app.api.common.auth.AuthConstants
import ru.bezdar.skip.app.api.request.controller.RequestController
import ru.bezdar.skip.app.api.request.model.body.CreateRequestBody
import ru.bezdar.skip.app.common.ApiVersion
import ru.bezdar.skip.app.common.extentions.getAuthorizedUserId
import ru.bezdar.skip.app.common.extentions.getWithVersion
import ru.bezdar.skip.app.common.extentions.postWithVersion
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
    }
}
