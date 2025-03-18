package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.error.RequestNotFound
import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.RequestDbDataSource
import ru.bezdar.skip.domain.request.model.Request

interface GetRequestsUseCase : UseCase<Unit, List<Request>>

class GetRequestsUseCaseImpl(
    private val requestDbDataSource: RequestDbDataSource,
) : GetRequestsUseCase {

    override suspend fun execute(param: Unit): List<Request> {
        val requests = requestDbDataSource.getRequests()

        if (requests.isNotEmpty()) {
            return requests
        } else {
            throw RequestNotFound()
        }
    }
}
