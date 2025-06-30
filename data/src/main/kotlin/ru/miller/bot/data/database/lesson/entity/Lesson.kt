package ru.miller.bot.data.database.lesson.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.timestamp
import ru.miller.bot.data.database.user.entity.UserEntity
import ru.miller.bot.data.database.user.entity.UserTable
import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.lesson.model.Lesson
import ru.miller.bot.domain.user.model.User
import java.util.UUID

object LessonTable : UUIDTable("lessons") {

    val startTime = timestamp("start_time")
    val endTime = timestamp("end_time")
    val conferenceLink = text("conference_link").nullable()

    val student_id = reference("student_id", UserTable, onDelete = ReferenceOption.CASCADE)
}

class LessonEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var startTime by LessonTable.startTime
    var endTime by LessonTable.endTime
    var conferenceLink by LessonTable.conferenceLink

    var student by UserEntity referencedOn LessonTable.student_id

    companion object : UUIDEntityClass<LessonEntity>(LessonTable)
}

fun LessonEntity.toDomain() = Lesson(
    id = Id<Lesson>(id.value),
    startTime = startTime,
    endTime = endTime,
    conferenceLink = conferenceLink,
    studentId = Id<User>(student.id.value),
)
