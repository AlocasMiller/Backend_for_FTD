package ru.bezdar.skip.app.api.user

import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject
import ru.bezdar.skip.app.api.common.auth.AuthConstants
import ru.bezdar.skip.app.api.user.controller.UserController
import ru.bezdar.skip.app.api.user.model.body.UpdateRoleBody
import ru.bezdar.skip.app.common.ApiVersion
import ru.bezdar.skip.app.common.extentions.getAuthorizedUserId
import ru.bezdar.skip.app.common.extentions.getWithVersion
import ru.bezdar.skip.app.common.extentions.patchWithVersion
import ru.bezdar.skip.app.common.extentions.respondSuccess

fun Route.configureUserRouting() {
    val controller by inject<UserController>()

    authenticate(AuthConstants.ACCESS_JWT_NAME) {
        getWithVersion<UserRoute.Users>(ApiVersion.V1) {
            val users = controller.getUsers()
            call.respondSuccess(users)
        }

        getWithVersion<UserRoute.Myself>(ApiVersion.V1) {
            val userId = call.getAuthorizedUserId()
            val user = controller.getMyself(userId)
            call.respondSuccess(user)
        }

        patchWithVersion<UserRoute.User>(ApiVersion.V1) { params ->
            val body = call.receive<UpdateRoleBody>()

            val user = controller.createUser(params.userId, body)
            call.respondSuccess(user)
        }
    }
}
