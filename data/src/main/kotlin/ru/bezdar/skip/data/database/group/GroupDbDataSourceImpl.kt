package ru.bezdar.skip.data.database.group

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SizedCollection
import ru.bezdar.skip.data.database.common.DatabaseDataSourse
import ru.bezdar.skip.data.database.group.entity.GroupEntity
import ru.bezdar.skip.data.database.group.entity.toDomain
import ru.bezdar.skip.data.database.user.entity.UserEntity
import ru.bezdar.skip.domain.common.error.UserNotFound
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.group.GroupDbDataSource
import ru.bezdar.skip.domain.group.model.Group
import ru.bezdar.skip.domain.group.model.params.NewGroup
import ru.bezdar.skip.domain.user.model.User

class GroupDbDataSourceImpl(override val database: Database) : GroupDbDataSource, DatabaseDataSourse {

    override suspend fun createGroup(params: NewGroup): Group = dbQuery {
        val groupEntity = GroupEntity.new {
            this.number = params.number
        }

        addStudents(groupEntity, params.students)
        groupEntity.toDomain()
    }

    private fun addStudents(group: GroupEntity, students: List<Id<User>>) {
        val toAdd = students.map { UserEntity.findById(it.value) ?: throw UserNotFound() }
        group.students = SizedCollection(group.students + toAdd)
        group.toDomain()
    }
}
