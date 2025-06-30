package ru.miller.bot.app.plugin

import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.application.log
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.statuspages.StatusPages
import ru.miller.bot.app.common.extentions.respondError
import ru.miller.bot.domain.common.error.ApplicationError
import ru.miller.bot.domain.common.error.BadRequestError
import ru.miller.bot.domain.common.error.ConflictError
import ru.miller.bot.domain.common.error.ForbiddenError
import ru.miller.bot.domain.common.error.NotFoundError

@Suppress("LongMethod")
fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<BadRequestError> { call, cause ->
            call.logError(cause)
            call.respondError(
                status = HttpStatusCode.BadRequest,
                errorCode = cause.code,
                message = cause.message
            )
        }

        exception<BadRequestException> { call, cause ->
            call.logError(cause)
            call.respondError(
                status = HttpStatusCode.BadRequest,
                errorCode = BadRequestError::class.java.simpleName,
                message = cause.message
            )
        }

        exception<ForbiddenError> { call, cause ->
            call.logError(cause)
            call.respondError(
                status = HttpStatusCode.Forbidden,
                errorCode = cause.code,
                message = cause.message
            )
        }

        exception<NotFoundError> { call, cause ->
            call.logError(cause)
            call.respondError(
                status = HttpStatusCode.NotFound,
                errorCode = cause.code,
                message = cause.message
            )
        }

        exception<ConflictError> { call, cause ->
            call.logError(cause)
            call.respondError(
                status = HttpStatusCode.Conflict,
                errorCode = cause.code,
                message = cause.message
            )
        }

        exception<Exception> { call, cause ->
            call.logError(cause)
            call.respondError(
                status = InternalServerError,
                errorCode = InternalServerError::class.java.simpleName,
                message = cause.message
            )
        }

        exception<NoSuchMethodError> { call, cause ->
            call.logError(cause)
            call.respondError(
                status = InternalServerError,
                errorCode = InternalServerError::class.java.simpleName,
                message = cause.message
            )
        }
    }
}

private fun ApplicationCall.logError(error: Throwable) {
    val (code: String, message: String?) = if (error is ApplicationError) {
        error.code to error.message
    } else {
        error::class.java.simpleName to error.message
    }
    application.log.error("$code: $message ${error.stackTraceToString()}")
}
