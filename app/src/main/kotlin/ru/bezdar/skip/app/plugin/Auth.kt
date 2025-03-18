package ru.bezdar.skip.app.plugin

import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.interfaces.Payload
import io.ktor.server.application.Application
import io.ktor.server.auth.AuthenticationConfig
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.jwt
import org.koin.ktor.ext.inject
import ru.bezdar.skip.app.api.auth.token.TokenValidator
import ru.bezdar.skip.app.api.common.auth.AuthConstants
import ru.bezdar.skip.app.api.common.auth.UserPrincipal
import ru.bezdar.skip.app.api.common.model.IdDto

fun Application.configureAuth() {
    val tokenValidator by inject<TokenValidator>()

    authentication {
        baseJwt(name = AuthConstants.ACCESS_JWT_NAME, verifier = tokenValidator.accessTokenVerifier) { payload ->
            tokenValidator.getUserIdFromPayload(payload)
        }

        baseJwt(name = AuthConstants.REFRESH_JWT_NAME, verifier = tokenValidator.refreshTokenVerifier) { payload ->
            tokenValidator.getUserIdFromPayload(payload)
        }
    }
}

private fun AuthenticationConfig.baseJwt(name: String, verifier: JWTVerifier, userIdExtractor: (Payload) -> IdDto?) {
    jwt(name = name) {
        verifier(verifier)
        validate { credentials ->
            val userId = userIdExtractor.invoke(credentials.payload)
            userId?.let { UserPrincipal(it) }
        }
    }
}
