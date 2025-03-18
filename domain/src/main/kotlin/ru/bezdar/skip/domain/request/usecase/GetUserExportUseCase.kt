package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.RequestDbDataSource
import ru.bezdar.skip.domain.request.file.FileDbDataSource
import ru.bezdar.skip.domain.request.file.common.model.Zip
import ru.bezdar.skip.domain.user.model.User

interface GetUserExportUseCase : UseCase<Id<User>, Zip>

class GetUserExportUseCaseImpl(
    private val requestDbDataSource: RequestDbDataSource,
    private val fileDbDataSource: FileDbDataSource,
) : GetUserExportUseCase {

    override suspend fun execute(param: Id<User>): Zip {
        TODO("Not yet implemented")
    }
}
