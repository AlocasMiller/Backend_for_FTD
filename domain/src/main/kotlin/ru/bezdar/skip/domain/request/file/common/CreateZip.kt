package ru.bezdar.skip.domain.request.file.common

import ru.bezdar.skip.domain.request.file.common.model.Zip
import ru.bezdar.skip.domain.request.file.model.File
import java.io.ByteArrayOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

fun createZip(files: List<File>): Zip {
    val outputStream = ByteArrayOutputStream()

    ZipOutputStream(outputStream).use { zipOut ->
        files.forEach { file ->
            val entry = ZipEntry(file.id.value.toString().substring(0, 8) + "." + file.fileName)
            zipOut.putNextEntry(entry)
            zipOut.write(file.fileData)
            zipOut.closeEntry()
        }
    }
    return Zip(outputStream.toByteArray())
}
