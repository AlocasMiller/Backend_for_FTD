package ru.bezdar.skip.domain.group.usecase

import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.group.GroupDbDataSource
import ru.bezdar.skip.domain.group.model.Group
import ru.bezdar.skip.domain.group.model.params.NewGroup

interface CreateGroupUseCase : UseCase<NewGroup, Group>

class CreateGroupUseCaseImpl(
    private val groupDbDataSource: GroupDbDataSource,
) : CreateGroupUseCase {

    override suspend fun execute(params: NewGroup): Group {
        return groupDbDataSource.createGroup(params)
    }
}
