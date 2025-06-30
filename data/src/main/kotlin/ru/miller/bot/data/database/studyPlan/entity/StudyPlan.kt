package ru.miller.bot.data.database.studyPlan.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.studyPlan.model.StudyPlan
import java.util.UUID

object StudyPlanTable : UUIDTable("study_plans") {

    val name = varchar("name", 255)
    val link = varchar("link", 255)
}

class StudyPlanEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var name by StudyPlanTable.name
    var link by StudyPlanTable.link

    companion object : UUIDEntityClass<StudyPlanEntity>(StudyPlanTable)
}

fun StudyPlanEntity.toDomain() = StudyPlan(
    id = Id<StudyPlan>(id.value),
    name = name,
    link = link,
)
