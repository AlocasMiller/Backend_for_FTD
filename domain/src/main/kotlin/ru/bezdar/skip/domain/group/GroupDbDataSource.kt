package ru.bezdar.skip.domain.group

import ru.bezdar.skip.domain.group.model.Group
import ru.bezdar.skip.domain.group.model.params.NewGroup

interface GroupDbDataSource {

    suspend fun createGroup(params: NewGroup): Group

    suspend fun getGroups(): List<Group>
}
