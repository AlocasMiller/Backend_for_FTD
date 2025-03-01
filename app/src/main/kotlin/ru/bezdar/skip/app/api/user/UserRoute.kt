package ru.bezdar.skip.app.api.user

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import ru.bezdar.skip.app.api.common.model.IdDto

sealed class UserRoute {

    @Serializable
    @Resource("/users")
    object Users : UserRoute()

    @Serializable
    @Resource("/users/{userId}")
    class User(val userId: IdDto) : UserRoute()
}
