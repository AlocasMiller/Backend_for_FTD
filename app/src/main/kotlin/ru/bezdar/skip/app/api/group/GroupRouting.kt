package ru.bezdar.skip.app.api.group

import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject
import ru.bezdar.skip.app.api.common.auth.AuthConstants
import ru.bezdar.skip.app.api.group.controller.GroupController
import ru.bezdar.skip.app.api.group.model.body.CreateGroupBody
import ru.bezdar.skip.app.common.ApiVersion
import ru.bezdar.skip.app.common.extentions.getWithVersion
import ru.bezdar.skip.app.common.extentions.postWithVersion
import ru.bezdar.skip.app.common.extentions.receiveAndValidate
import ru.bezdar.skip.app.common.extentions.respondCreated
import ru.bezdar.skip.app.common.extentions.respondSuccess

fun Route.configureGroupRouting() {
    val controller by inject<GroupController>()

    authenticate(AuthConstants.ACCESS_JWT_NAME) {
        getWithVersion<GroupRoute.Groups>(ApiVersion.V1) {
            val groups = controller.getGroups()
            call.respondSuccess(groups)
        }

        postWithVersion<GroupRoute.Groups>(ApiVersion.V1) {
            val body = call.receiveAndValidate<CreateGroupBody>()

            val group = controller.createGroup(body)
            call.respondCreated(group)
        }
    }

}
