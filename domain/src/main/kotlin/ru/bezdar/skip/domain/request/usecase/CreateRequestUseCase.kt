package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.RequestDbDataSource
import ru.bezdar.skip.domain.request.model.Request
import ru.bezdar.skip.domain.request.model.params.NewRequest

interface CreateRequestUseCase : UseCase<NewRequest, Request>

class CreateRequestUseCaseImpl(
    private val requestDbDataSource: RequestDbDataSource,
) : CreateRequestUseCase {

    override suspend fun execute(param: NewRequest): Request {

        val request = requestDbDataSource.addRequest(param)

        return requestDbDataSource.getRequestById(request.id)!!
    }
}
