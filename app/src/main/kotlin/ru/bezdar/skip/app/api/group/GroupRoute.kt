package ru.bezdar.skip.app.api.group

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

sealed class GroupRoute {

    @Serializable
    @Resource("/groups")
    data object Groups : GroupRoute()
}
