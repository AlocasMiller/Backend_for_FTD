package ru.bezdar.skip.domain.request.usecase

import ru.bezdar.skip.domain.common.usecase.UseCase
import ru.bezdar.skip.domain.request.file.FileDbDataSource
import ru.bezdar.skip.domain.request.file.model.File
import ru.bezdar.skip.domain.request.file.model.params.NewFile
import ru.bezdar.skip.domain.request.model.params.UploadFile

interface UploadFileUseCase : UseCase<UploadFile, List<File>>

class UploadFileUseCaseImpl(
    private val fileDbDataSource: FileDbDataSource,
) : UploadFileUseCase {
    override suspend fun execute(param: UploadFile): List<File> {
        val files: MutableList<File> = mutableListOf()
        for (file in param.partData) {
            files.add(fileDbDataSource.addFile(NewFile(param.requestId, file.key, file.value)))
        }

        return files
    }
}
