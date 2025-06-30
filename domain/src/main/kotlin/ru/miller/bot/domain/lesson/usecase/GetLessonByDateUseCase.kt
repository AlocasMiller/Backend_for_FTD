package ru.miller.bot.domain.lesson.usecase

import ru.miller.bot.domain.common.usecase.UseCase
import ru.miller.bot.domain.lesson.LessonDbDataSource
import ru.miller.bot.domain.lesson.model.Lesson
import java.time.Instant

interface GetLessonByDateUseCase : UseCase<Instant, List<Lesson>>

class GetLessonByDateUseCaseImpl(
    private val lessonDbDataSource: LessonDbDataSource,
) : GetLessonByDateUseCase {
    override suspend fun execute(param: Instant): List<Lesson> {
        return lessonDbDataSource.getLessonByDate(param)
    }
}
