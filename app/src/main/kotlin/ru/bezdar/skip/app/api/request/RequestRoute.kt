package ru.bezdar.skip.app.api.request

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

sealed class RequestRoute {

    @Serializable
    @Resource("/requests")
    data object Requests : RequestRoute()

}
