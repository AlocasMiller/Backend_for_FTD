package ru.bezdar.skip.app.api.auth.token

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Payload
import ru.bezdar.skip.app.api.common.JwtConfig
import ru.bezdar.skip.app.api.common.model.IdDto
import ru.bezdar.skip.app.common.extentions.toUUIDOrNull
import ru.bezdar.skip.domain.auth.TokenManager
import ru.bezdar.skip.domain.auth.model.Token
import ru.bezdar.skip.domain.auth.model.TokenPair
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.user.model.User
import java.time.Instant
import java.util.Date

class JwtTokenManagerImpl(private val config: JwtConfig) : TokenManager, TokenValidator {
    private val accessTokenAlgorithm = Algorithm.HMAC256(config.accessTokenSecret.toByteArray())
    private val refreshTokenAlgorithm = Algorithm.HMAC256(config.refreshTokenSecret.toByteArray())

    override val accessTokenVerifier: JWTVerifier = JWT.require(accessTokenAlgorithm).build()
    override val refreshTokenVerifier: JWTVerifier = JWT.require(refreshTokenAlgorithm).build()

    override fun calculateTokenExpiresIn(validityTime: Long): Instant =
        Instant.now().plusSeconds(validityTime)

    override fun generateNewTokenPair(userId: Id<User>): TokenPair {
        val accessTokenExpiresIn = calculateTokenExpiresIn(config.accessTokenValidity)
        val refreshTokenExpiresIn = calculateTokenExpiresIn(config.refreshTokenValidity)

        val accessToken = Token(
            token = generateAccessToken(userId, accessTokenExpiresIn),
            expireAt = accessTokenExpiresIn
        )
        val refreshToken = Token(
            token = generateRefreshToken(userId, refreshTokenExpiresIn),
            expireAt = refreshTokenExpiresIn
        )

        return TokenPair(accessToken, refreshToken)
    }

    private fun generateAccessToken(userId: Id<User>, expiresIn: Instant): String = JWT.create()
        .withClaim(KEY_CLAIM_USER, userId.value.toString())
        .withExpiresAt(Date.from(expiresIn))
        .sign(accessTokenAlgorithm)

    private fun generateRefreshToken(userId: Id<User>, expiresIn: Instant): String = JWT.create()
        .withClaim(KEY_CLAIM_USER, userId.value.toString())
        .withExpiresAt(Date.from(expiresIn))
        .sign(refreshTokenAlgorithm)

    override fun getUserIdFromPayload(payload: Payload): IdDto? =
        payload.claims?.get(KEY_CLAIM_USER)?.asString()?.toUUIDOrNull()?.let { IdDto(it) }

    private companion object {
        const val KEY_CLAIM_USER = "userId"
    }
}
