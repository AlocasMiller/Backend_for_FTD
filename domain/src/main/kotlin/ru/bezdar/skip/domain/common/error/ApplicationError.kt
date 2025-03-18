package ru.bezdar.skip.domain.common.error

abstract class ApplicationError : Throwable() {

    open val code: String = this::class.java.simpleName
}

open class UnauthorizedError(override val message: String? = null) : ApplicationError()

abstract class ForbiddenError : ApplicationError()

abstract class NotFoundError : ApplicationError()

class UserNotFound(override val message: String = "User with such id not found") : NotFoundError()
class GroupNotFound(override val message: String? = "Group with such id not found") : NotFoundError()
class RequestNotFound(override val message: String? = "Request with such id not found") : NotFoundError()
class FilesNotFound(override val message: String? = "Files with APPROVED not found") : NotFoundError()

abstract class BadRequestError : ApplicationError()

class InvalidFields(override val message: String? = null) : BadRequestError()
class InvalidUUID(override val message: String? = "Provided invalid UUID") : BadRequestError()
class InvalidRefreshToken(override val message: String? = "Provided invalid refresh token") : BadRequestError()

abstract class ConflictError : ApplicationError()
