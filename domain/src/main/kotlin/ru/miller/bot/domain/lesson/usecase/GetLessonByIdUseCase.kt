package ru.miller.bot.domain.lesson.usecase

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.common.usecase.UseCase
import ru.miller.bot.domain.lesson.LessonDbDataSource
import ru.miller.bot.domain.lesson.model.Lesson

interface GetLessonByIdUseCase : UseCase<Id<Lesson>, Lesson>

class GetLessonByIdUseCaseImpl(
    private val lessonDbDataSource: LessonDbDataSource,
) : GetLessonByIdUseCase {
    override suspend fun execute(param: Id<Lesson>): Lesson {
        return lessonDbDataSource.getLessonById(param)
    }
}
