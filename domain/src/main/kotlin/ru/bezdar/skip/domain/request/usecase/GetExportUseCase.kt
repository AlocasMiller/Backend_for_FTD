package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.error.FilesNotFound
import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.file.FileDbDataSource
import ru.bezdar.skip.domain.request.file.common.createZip
import ru.bezdar.skip.domain.request.file.common.model.Zip

interface GetExportUseCase : UseCase<Unit, Zip>

class GetExportUseCaseImpl(
    private val fileDbDataSource: FileDbDataSource,
) : GetExportUseCase {

    override suspend fun execute(params: Unit): Zip {
        val files = fileDbDataSource.getAllFiles()

        if (files.isNotEmpty()) {
            return createZip(files)
        } else {
            throw FilesNotFound()
        }
    }
}
