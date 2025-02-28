package ru.bezdar.skip.app.modules

import org.koin.core.module.dsl.binds
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bezdar.skip.app.api.auth.token.JwtTokenManagerImpl
import ru.bezdar.skip.app.api.auth.token.TokenValidator
import ru.bezdar.skip.domain.auth.TokenManager

val authModule = module {
    singleOf(::JwtTokenManagerImpl) {
        binds(listOf(TokenManager::class, TokenValidator::class))
    }
}
