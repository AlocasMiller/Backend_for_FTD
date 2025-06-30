package ru.miller.bot.domain.lesson.usecase

import ru.miller.bot.domain.common.usecase.UseCase
import ru.miller.bot.domain.lesson.LessonDbDataSource
import ru.miller.bot.domain.lesson.model.Lesson
import ru.miller.bot.domain.lesson.model.params.CreateUpdateLessonParams

interface CreateLessonUseCase : UseCase<CreateUpdateLessonParams, Lesson>

class CreateLessonUseCaseImpl(
    private val lessonDbDataSource: LessonDbDataSource,
) : CreateLessonUseCase {
    override suspend fun execute(param: CreateUpdateLessonParams): Lesson {
        return lessonDbDataSource.createLesson(param)
    }
}
