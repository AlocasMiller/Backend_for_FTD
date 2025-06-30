package ru.miller.bot.app.api.user

import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject
import ru.miller.bot.app.api.common.model.IdDto
import ru.miller.bot.app.api.user.controller.UserController
import ru.miller.bot.app.api.user.model.body.CreateUpdateUserBody
import ru.miller.bot.app.common.ApiVersion
import ru.miller.bot.app.common.extentions.deleteWithVersion
import ru.miller.bot.app.common.extentions.getWithVersion
import ru.miller.bot.app.common.extentions.postWithVersion
import ru.miller.bot.app.common.extentions.putWithVersion
import ru.miller.bot.app.common.extentions.respondNoContent
import ru.miller.bot.app.common.extentions.respondSuccess

fun Route.configureUserRouting() {
    val controller by inject<UserController>()

    postWithVersion<UserRoute.Users>(ApiVersion.V1) {
        val body = call.receive<CreateUpdateUserBody>()

        val user = controller.createUser(body)
        call.respondSuccess(user)
    }

    getWithVersion<UserRoute.Users>(ApiVersion.V1) {
        val users = controller.getUsers()
        call.respondSuccess(users)
    }

    getWithVersion<UserRoute.UserTg>(ApiVersion.V1) { params ->
        val user = controller.getUserByTelegramId(params.telegramId)
        call.respondSuccess(user)
    }

    getWithVersion<UserRoute.User>(ApiVersion.V1) { params ->
        val user = controller.getUserById(IdDto(params.userId))
        call.respondSuccess(user)
    }

    putWithVersion<UserRoute.User>(ApiVersion.V1) {params ->
        val body = call.receive<CreateUpdateUserBody>()

        val user = controller.updateUserById(IdDto(params.userId), body)
        call.respondSuccess(user)
    }

    deleteWithVersion<UserRoute.User>(ApiVersion.V1) { params ->
        controller.deleteUserById(IdDto(params.userId))
        call.respondNoContent()
    }
}
