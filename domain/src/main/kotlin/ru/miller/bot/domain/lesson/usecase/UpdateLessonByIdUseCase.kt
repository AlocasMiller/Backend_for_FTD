package ru.miller.bot.domain.lesson.usecase

import ru.miller.bot.domain.common.usecase.UseCase
import ru.miller.bot.domain.lesson.LessonDbDataSource
import ru.miller.bot.domain.lesson.model.Lesson
import ru.miller.bot.domain.lesson.model.params.CreateUpdateLessonParams

interface UpdateLessonByIdUseCase : UseCase<CreateUpdateLessonParams, Lesson>

class UpdateLessonByIdUseCaseImpl(
    private val lessonDbDataSource: LessonDbDataSource,
) : UpdateLessonByIdUseCase {
    override suspend fun execute(param: CreateUpdateLessonParams): Lesson {
        return lessonDbDataSource.updateLessonById(param)
    }
}
