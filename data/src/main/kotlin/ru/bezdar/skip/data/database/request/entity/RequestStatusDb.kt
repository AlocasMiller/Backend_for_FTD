package ru.bezdar.skip.data.database.request.entity

import ru.bezdar.skip.domain.request.model.RequestStatus

enum class RequestStatusDb {
    PENDING,
    APPROVED,
    DECLINED,
}

fun RequestStatusDb.toDomain() = when (this) {
    RequestStatusDb.PENDING -> RequestStatus.PENDING
    RequestStatusDb.APPROVED -> RequestStatus.APPROVED
    RequestStatusDb.DECLINED -> RequestStatus.DECLINED
}

fun RequestStatus.toDb() = when (this) {
    RequestStatus.PENDING -> RequestStatusDb.PENDING
    RequestStatus.APPROVED -> RequestStatusDb.APPROVED
    RequestStatus.DECLINED -> RequestStatusDb.DECLINED
}
