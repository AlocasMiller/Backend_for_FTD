package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.error.FilesNotFound
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.file.FileDbDataSource
import ru.bezdar.skip.domain.request.file.common.createZip
import ru.bezdar.skip.domain.request.file.common.model.Zip
import ru.bezdar.skip.domain.user.model.User

interface GetUserExportUseCase : UseCase<Id<User>, Zip>

class GetUserExportUseCaseImpl(
    private val fileDbDataSource: FileDbDataSource,
) : GetUserExportUseCase {

    override suspend fun execute(param: Id<User>): Zip {
        val files = fileDbDataSource.getFilesByUserId(param)

        if (files.isNotEmpty()) {
            return createZip(files)
        } else {
            throw FilesNotFound()
        }
    }
}
