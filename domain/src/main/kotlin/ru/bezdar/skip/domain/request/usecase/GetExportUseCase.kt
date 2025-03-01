package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.RequestDbDataSource
import ru.bezdar.skip.domain.request.file.model.File

interface GetExportUseCase : UseCase<Unit, List<File>>

class GetExportUseCaseImpl(
    private val requestDbDataSource: RequestDbDataSource
) : GetExportUseCase {

    override suspend fun execute(params: Unit): List<File> {
        TODO("Not yet implemented")
    }
}
