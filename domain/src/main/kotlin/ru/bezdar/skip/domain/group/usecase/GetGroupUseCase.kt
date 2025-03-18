package ru.bezdar.skip.domain.group.usecase

import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.group.GroupDbDataSource
import ru.bezdar.skip.domain.group.model.Group

interface GetGroupUseCase : UseCase<Unit, List<Group>>

class GetGroupUseCaseImpl(
    private val groupDbDataSource: GroupDbDataSource
) : GetGroupUseCase {

    override suspend fun execute(param: Unit): List<Group> {
        val groups = groupDbDataSource.getGroups()
        return groups
    }
}
