package ru.bezdar.skip.data.database.request.entity

import ru.bezdar.skip.domain.request.model.RequestReason

enum class RequestReasonDb {
    FAMILY,
    ILLNESS,
    STUDY_ACTIVITY,
}

fun RequestReasonDb.toDomain() = when (this) {
    RequestReasonDb.FAMILY -> RequestReason.FAMILY
    RequestReasonDb.ILLNESS -> RequestReason.ILLNESS
    RequestReasonDb.STUDY_ACTIVITY -> RequestReason.STUDY_ACTIVITY
}

fun RequestReason.toDb() = when (this) {
    RequestReason.FAMILY -> RequestReasonDb.FAMILY
    RequestReason.ILLNESS -> RequestReasonDb.ILLNESS
    RequestReason.STUDY_ACTIVITY -> RequestReasonDb.STUDY_ACTIVITY
}
