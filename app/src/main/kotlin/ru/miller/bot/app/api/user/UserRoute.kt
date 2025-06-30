package ru.miller.bot.app.api.user

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import ru.miller.bot.app.common.serializers.UUIDAsStringSerializer
import java.util.UUID

sealed class UserRoute {

    @Serializable
    @Resource("/users")
    object Users : UserRoute()

    @Serializable
    @Resource("/users/{userId}")
    class User(
        @Serializable(with = UUIDAsStringSerializer::class)
        val userId: UUID,
    ) : UserRoute()

    @Serializable
    @Resource("/users/tg/{telegramId}")
    class UserTg(val telegramId: Int) : UserRoute()
}
