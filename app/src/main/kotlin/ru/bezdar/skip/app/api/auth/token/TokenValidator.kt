package ru.bezdar.skip.app.api.auth.token

import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.interfaces.Payload
import ru.bezdar.skip.app.api.common.model.IdDto

interface TokenValidator {
    val accessTokenVerifier: JWTVerifier
    val refreshTokenVerifier: JWTVerifier

    fun getUserIdFromPayload(payload: Payload): IdDto?
}
