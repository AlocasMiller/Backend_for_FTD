package ru.bezdar.skip.app.common.extentions

import io.konform.validation.Invalid
import io.konform.validation.Validation
import ru.bezdar.skip.domain.common.error.InvalidFields

fun <T> Validation<T>.validateAndThrow(value: T) {
    val result = this.validate(value)
    if (result is Invalid<T>) {
        val errors = result.errors.joinToString { error ->
            "${error.dataPath.drop(1)} ${error.message}".trim()
        }
        throw InvalidFields(errors)
    }
}
