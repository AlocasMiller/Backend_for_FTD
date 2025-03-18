package ru.bezdar.skip.domain.auth

import ru.bezdar.skip.domain.auth.model.TokenPair
import ru.bezdar.skip.domain.common.model.Id
import ru.bezdar.skip.domain.user.model.User
import java.time.Instant

interface TokenManager {
    fun calculateTokenExpiresIn(validityTime: Long): Instant
    fun generateNewTokenPair(userId: Id<User>): TokenPair
}
