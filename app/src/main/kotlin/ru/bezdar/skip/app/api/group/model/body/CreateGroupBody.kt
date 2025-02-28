package ru.bezdar.skip.app.api.group.model.body

import io.konform.validation.Validation
import io.konform.validation.jsonschema.minItems
import kotlinx.serialization.Serializable
import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.api.common.model.toDomain
import ru.bezdar.skip.app.common.validation.Validated
import ru.bezdar.skip.domain.group.model.params.NewGroup

@Serializable
data class CreateGroupBody(
    val number: Int,
    val students: List<IdDto>,
) : Validated<CreateGroupBody> {
    override val validationRule: Validation<CreateGroupBody> = Validation {
        CreateGroupBody::students required {
            minItems(1)
        }
    }
}

fun CreateGroupBody.toDomain() = NewGroup(
    number = number,
    students = students.map(IdDto::toDomain),
)
