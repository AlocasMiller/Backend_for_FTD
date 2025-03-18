package ru.bezdar.skip.app.api.common.auth

import io.ktor.server.auth.Principal
import ru.bezdar.skip.app.api.common.model.IdDto

data class UserPrincipal(
    val userId: IdDto,
) : Principal
