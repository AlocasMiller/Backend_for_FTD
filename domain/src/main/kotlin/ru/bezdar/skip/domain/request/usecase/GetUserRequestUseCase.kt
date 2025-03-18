package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.error.RequestNotFound
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.RequestDbDataSource
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.user.model.User

interface GetUserRequestUseCase : UseCase<Id<User>, List<Request>>

class GetUserRequestUseCaseImpl(
    private val requestsDbDataSource: RequestDbDataSource,
) : GetUserRequestUseCase {

    override suspend fun execute(param: Id<User>): List<Request> {
        val requests = requestsDbDataSource.getRequestByUserId(param)

        if (requests.isNotEmpty()) {
            return requests
        } else {
            throw RequestNotFound()
        }
    }
}
