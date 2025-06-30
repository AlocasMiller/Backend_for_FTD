package ru.miller.bot.domain.lesson.usecase

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.common.usecase.UseCase
import ru.miller.bot.domain.lesson.LessonDbDataSource
import ru.miller.bot.domain.lesson.model.Lesson

interface DeleteLessonByIdUseCase : UseCase<Id<Lesson>, Unit>

class DeleteLessonByIdUseCaseImpl(
    private val lessonDbDataSource: LessonDbDataSource,
) : DeleteLessonByIdUseCase {
    override suspend fun execute(param: Id<Lesson>) {
        lessonDbDataSource.deleteLessonById(param)
    }
}
