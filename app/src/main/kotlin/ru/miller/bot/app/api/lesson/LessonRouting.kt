package ru.miller.bot.app.api.lesson

import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject
import ru.miller.bot.app.api.common.model.IdDto
import ru.miller.bot.app.api.lesson.controller.LessonController
import ru.miller.bot.app.api.lesson.model.body.CreateLessonBody
import ru.miller.bot.app.common.ApiVersion
import ru.miller.bot.app.common.extentions.deleteWithVersion
import ru.miller.bot.app.common.extentions.getWithVersion
import ru.miller.bot.app.common.extentions.postWithVersion
import ru.miller.bot.app.common.extentions.putWithVersion
import ru.miller.bot.app.common.extentions.respondNoContent
import ru.miller.bot.app.common.extentions.respondSuccess
import ru.miller.bot.domain.common.error.InvalidParameters

fun Route.configureLessonRouting() {
    val controller by inject<LessonController>()

    postWithVersion<LessonRoute.Lessons>(ApiVersion.V1) {
        val body = call.receive<CreateLessonBody>()

        val lesson = controller.createLesson(body)
        call.respondSuccess(lesson)
    }

    getWithVersion<LessonRoute.Lessons>(ApiVersion.V1) { params ->
        if (params.date == null) {
            throw InvalidParameters()
        }
        val lesson = controller.getLessonByDate(params.date)
        call.respondSuccess(lesson)
    }

    getWithVersion<LessonRoute.Lesson>(ApiVersion.V1) { params ->
        val lesson = controller.getLessonById(IdDto(params.lessonsId))

        call.respondSuccess(lesson)
    }

    putWithVersion<LessonRoute.Lesson>(ApiVersion.V1) { params ->
        val body = call.receive<CreateLessonBody>()

        val lesson = controller.updateLessonById(IdDto(params.lessonsId), body)
        call.respondSuccess(lesson)
    }

    deleteWithVersion<LessonRoute.Lesson>(ApiVersion.V1) { params ->
        controller.deleteLessonById(IdDto(params.lessonsId))
        call.respondNoContent()
    }
}
