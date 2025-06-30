package ru.miller.bot.domain.lesson

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.lesson.model.Lesson
import ru.miller.bot.domain.lesson.model.params.CreateUpdateLessonParams
import java.time.Instant

interface LessonDbDataSource {

    suspend fun createLesson(params: CreateUpdateLessonParams): Lesson

    suspend fun getLessonById(id: Id<Lesson>): Lesson
    suspend fun getLessonByDate(date: Instant): List<Lesson>

    suspend fun updateLessonById(params: CreateUpdateLessonParams): Lesson

    suspend fun deleteLessonById(id: Id<Lesson>)
}
