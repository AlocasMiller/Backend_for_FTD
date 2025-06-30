package ru.miller.bot.data.database.user.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import ru.miller.bot.data.database.studyPlan.entity.StudyPlanEntity
import ru.miller.bot.data.database.studyPlan.entity.StudyPlanTable
import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.studyPlan.model.StudyPlan
import ru.miller.bot.domain.user.model.User
import java.util.UUID

object UserTable : UUIDTable("users") {

    val telegram_id = integer("telegram_id")
    val name = varchar("name", 255)
    val phone = varchar("phone", 20)
    val rate = integer("rate")

    val studyPlan = reference("study_plan", StudyPlanTable).nullable()
}

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var telegramId by UserTable.telegram_id
    var name by UserTable.name
    var phone by UserTable.phone
    var rate by UserTable.rate

    var studyPlan by StudyPlanEntity optionalReferencedOn UserTable.studyPlan

    companion object : UUIDEntityClass<UserEntity>(UserTable)
}

fun UserEntity.toDomain() = User(
    id = Id<User>(id.value),
    telegramId = telegramId,
    name = name,
    phone = phone,
    rate = rate,
    studyPlanId = studyPlan?.id?.value?.let { Id<StudyPlan>(it) },
)
