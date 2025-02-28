package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.RequestDbDataSource
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.user.model.User

interface GetRequestsUseCase : UseCase<Id<User>, List<Request>>

class GetRequestsUseCaseImpl(
    private val requestDbDataSource: RequestDbDataSource,
) : GetRequestsUseCase {

    override suspend fun execute(param: Id<User>): List<Request> {
        val requests = requestDbDataSource.getRequests()
        return requests
    }
}
