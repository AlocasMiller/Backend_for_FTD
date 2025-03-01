package ru.bezdar.skip.app.api.request

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import ru.bezdar.skip.app.api.common.model.IdDto

sealed class RequestRoute {

    @Serializable
    @Resource("/requests")
    data object Requests : RequestRoute()

    @Serializable
    @Resource("/requests/export")
    object RequestsExport : RequestRoute()

    @Serializable
    @Resource("/requests/{userId}")
    class Request(val userId: IdDto) : RequestRoute()

    @Serializable
    @Resource("/requests/{userId}/export")
    class RequestExport(val userId: IdDto) : RequestRoute()

    @Serializable
    @Resource("/requests/{requestId}")
    class RequestUpdate(val requestId: IdDto) : RequestRoute()
}
