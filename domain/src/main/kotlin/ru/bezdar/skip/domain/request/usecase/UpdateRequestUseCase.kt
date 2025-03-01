package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.RequestDbDataSource
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.request.model.params.UpdateRequest

interface UpdateRequestUseCase : UseCase<UpdateRequest, Request>

class UpdateRequestUseCaseImpl(
    private val requestDbDataSource: RequestDbDataSource,
) : UpdateRequestUseCase {

    override suspend fun execute(param: UpdateRequest): Request {
        return requestDbDataSource.updateRequest(param)
    }
}
