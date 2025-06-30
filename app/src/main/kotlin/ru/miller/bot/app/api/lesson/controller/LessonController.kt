package ru.miller.bot.app.api.lesson.controller

import ru.miller.bot.app.api.common.model.IdDto
import ru.miller.bot.app.api.common.model.toDomain
import ru.miller.bot.app.api.lesson.model.body.CreateLessonBody
import ru.miller.bot.app.api.lesson.model.body.toDomain
import ru.miller.bot.app.api.lesson.model.response.LessonResponse
import ru.miller.bot.app.api.lesson.model.response.toResponse
import ru.miller.bot.domain.lesson.model.Lesson
import ru.miller.bot.domain.lesson.usecase.CreateLessonUseCase
import ru.miller.bot.domain.lesson.usecase.DeleteLessonByIdUseCase
import ru.miller.bot.domain.lesson.usecase.GetLessonByDateUseCase
import ru.miller.bot.domain.lesson.usecase.GetLessonByIdUseCase
import ru.miller.bot.domain.lesson.usecase.UpdateLessonByIdUseCase
import java.time.Instant

class LessonController(
    private val createLessonUseCase: CreateLessonUseCase,
    private val deleteLessonByIdUseCase: DeleteLessonByIdUseCase,
    private val getLessonByDateUseCase: GetLessonByDateUseCase,
    private val getLessonByIdUseCase: GetLessonByIdUseCase,
    private val updateLessonByIdUseCase: UpdateLessonByIdUseCase,
) {
    suspend fun createLesson(body: CreateLessonBody): LessonResponse {
        return createLessonUseCase(body.toDomain(null)).getOrThrow().toResponse()
    }

    suspend fun deleteLessonById(id: IdDto) {
        deleteLessonByIdUseCase(id.toDomain<Lesson>()).getOrThrow()
    }

    suspend fun getLessonByDate(date: Instant): List<LessonResponse> {
        return getLessonByDateUseCase(date).getOrThrow().map { it.toResponse() }
    }

    suspend fun getLessonById(id: IdDto): LessonResponse {
        return getLessonByIdUseCase(id.toDomain<Lesson>()).getOrThrow().toResponse()
    }

    suspend fun updateLessonById(id: IdDto, body: CreateLessonBody): LessonResponse {
        return updateLessonByIdUseCase(body.toDomain(id)).getOrThrow().toResponse()
    }
}
