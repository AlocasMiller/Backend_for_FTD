package ru.bezdar.skip.app.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bezdar.skip.domain.group.usecase.CreateGroupUseCase
import ru.bezdar.skip.domain.group.usecase.CreateGroupUseCaseImpl
import ru.bezdar.skip.domain.group.usecase.GetGroupUseCase
import ru.bezdar.skip.domain.group.usecase.GetGroupUseCaseImpl
import ru.bezdar.skip.domain.request.usecase.CreateRequestUseCase
import ru.bezdar.skip.domain.request.usecase.CreateRequestUseCaseImpl
import ru.bezdar.skip.domain.request.usecase.GetExportUseCase
import ru.bezdar.skip.domain.request.usecase.GetExportUseCaseImpl
import ru.bezdar.skip.domain.request.usecase.GetRequestsUseCase
import ru.bezdar.skip.domain.request.usecase.GetRequestsUseCaseImpl
import ru.bezdar.skip.domain.request.usecase.GetUserExportUseCase
import ru.bezdar.skip.domain.request.usecase.GetUserExportUseCaseImpl
import ru.bezdar.skip.domain.request.usecase.GetUserRequestUseCase
import ru.bezdar.skip.domain.request.usecase.GetUserRequestUseCaseImpl
import ru.bezdar.skip.domain.request.usecase.UpdateRequestUseCase
import ru.bezdar.skip.domain.request.usecase.UpdateRequestUseCaseImpl
import ru.bezdar.skip.domain.user.usecase.UpdateUserRoleUseCase
import ru.bezdar.skip.domain.user.usecase.UpdateUserRoleUseCaseImpl
import ru.bezdar.skip.domain.user.usecase.GetUserUseCase
import ru.bezdar.skip.domain.user.usecase.GetUserUseCaseImpl

val useCaseModule = module {

    // region Request
    factoryOf(::CreateRequestUseCaseImpl) bind CreateRequestUseCase::class
    factoryOf(::GetRequestsUseCaseImpl) bind GetRequestsUseCase::class
    factoryOf(::GetExportUseCaseImpl) bind  GetExportUseCase::class
    factoryOf(::GetUserExportUseCaseImpl) bind  GetUserExportUseCase::class
    factoryOf(::GetUserRequestUseCaseImpl) bind GetUserRequestUseCase::class
    factoryOf(::UpdateRequestUseCaseImpl) bind UpdateRequestUseCase::class
    // endregion

    // region Group
    factoryOf(::CreateGroupUseCaseImpl) bind CreateGroupUseCase::class
    factoryOf(::GetGroupUseCaseImpl) bind GetGroupUseCase::class
    // endregion

    // region User
    factoryOf(::GetUserUseCaseImpl) bind GetUserUseCase::class
    factoryOf(::UpdateUserRoleUseCaseImpl) bind UpdateUserRoleUseCase::class
    // endregion
}
