package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.RequestDbDataSource
import ru.bezdar.skip.domain.request.file.FileDbDataSource
import ru.bezdar.skip.domain.request.file.model.File
import ru.bezdar.skip.domain.user.model.User

interface GetUserExportUseCase : UseCase<Id<User>, List<File>>

class GetUserExportUseCaseImpl(
    private val requestDbDataSource: RequestDbDataSource,
    private val fileDbDataSource: FileDbDataSource,
) : GetUserExportUseCase {

    override suspend fun execute(param: Id<User>): List<File> {
        TODO("Not yet implemented")
    }
}
