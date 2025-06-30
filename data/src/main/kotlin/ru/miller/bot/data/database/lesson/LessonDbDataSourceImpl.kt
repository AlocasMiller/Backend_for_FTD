package ru.miller.bot.data.database.lesson

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import ru.miller.bot.data.database.common.DatabaseDataSourse
import ru.miller.bot.data.database.lesson.entity.LessonEntity
import ru.miller.bot.data.database.lesson.entity.LessonTable
import ru.miller.bot.data.database.lesson.entity.toDomain
import ru.miller.bot.data.database.user.entity.UserEntity
import ru.miller.bot.domain.common.error.LessonNotFound
import ru.miller.bot.domain.common.error.UserNotFound
import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.lesson.LessonDbDataSource
import ru.miller.bot.domain.lesson.model.Lesson
import ru.miller.bot.domain.lesson.model.params.CreateUpdateLessonParams
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId

class LessonDbDataSourceImpl(override val database: Database) : LessonDbDataSource, DatabaseDataSourse {

    override suspend fun createLesson(params: CreateUpdateLessonParams): Lesson = dbQuery{
        LessonEntity.new {
            startTime = params.startTime.minusSeconds(7*60*60)
            endTime = params.endTime.minusSeconds(7*60*60)
            conferenceLink = params.conferenceLink
            student = UserEntity.findById(params.studentId.value) ?: throw UserNotFound()
        }.toDomain()
    }

    override suspend fun getLessonById(id: Id<Lesson>): Lesson = dbQuery {
        LessonEntity.findById(id.value)?.toDomain() ?: throw LessonNotFound()
    }

    override suspend fun getLessonByDate(date: Instant): List<Lesson> = dbQuery {
        val zoneId = ZoneId.systemDefault()
        val startOfDay = date
            .atZone(zoneId)
            .toLocalDate()
            .atStartOfDay(zoneId)
            .toInstant()
        val endOfDay = date
            .atZone(zoneId)
            .toLocalDate()
            .atTime(LocalTime.MAX)
            .atZone(zoneId)
            .toInstant()

        LessonEntity.find { LessonTable.startTime greaterEq startOfDay and
                ( LessonTable.startTime lessEq endOfDay)
        }.map { it.toDomain() }
    }

    override suspend fun updateLessonById(params: CreateUpdateLessonParams): Lesson = dbQuery {
        params.id?.let { LessonEntity.findById(it.value) }?.apply {
            startTime = params.startTime.minusSeconds(7*60*60)
            endTime = params.endTime.minusSeconds(7*60*60)
            conferenceLink = params.conferenceLink
            student = UserEntity.findById(params.studentId.value) ?: throw UserNotFound()
        }?.toDomain() ?: throw LessonNotFound()
    }

    override suspend fun deleteLessonById(id: Id<Lesson>) = dbQueryWithoutResult {
        LessonEntity.findById(id.value)?.delete()
    }
}
