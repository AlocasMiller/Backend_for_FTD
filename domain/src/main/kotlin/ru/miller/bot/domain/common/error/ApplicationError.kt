package ru.miller.bot.domain.common.error

abstract class ApplicationError : Throwable() {

    open val code: String = this::class.java.simpleName
}

abstract class ForbiddenError : ApplicationError()

abstract class NotFoundError : ApplicationError()

class UserNotFound(override val message: String = "User with such id not found") : NotFoundError()
class StudyPlanNotFound(override val message: String? = "Study plan with such id not found") : NotFoundError()
class LessonNotFound(override val message: String? = "Lesson with such id not found") : NotFoundError()

abstract class BadRequestError : ApplicationError()

class InvalidFields(override val message: String? = null) : BadRequestError()
class InvalidUUID(override val message: String? = "Provided invalid UUID") : BadRequestError()
class InvalidParameters(override val message: String? = "Provided invalid parameters") : BadRequestError()

abstract class ConflictError : ApplicationError()
