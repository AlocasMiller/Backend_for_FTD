package ru.miller.bot.app.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.miller.bot.domain.lesson.usecase.CreateLessonUseCase
import ru.miller.bot.domain.lesson.usecase.CreateLessonUseCaseImpl
import ru.miller.bot.domain.lesson.usecase.DeleteLessonByIdUseCase
import ru.miller.bot.domain.lesson.usecase.DeleteLessonByIdUseCaseImpl
import ru.miller.bot.domain.lesson.usecase.GetLessonByDateUseCase
import ru.miller.bot.domain.lesson.usecase.GetLessonByDateUseCaseImpl
import ru.miller.bot.domain.lesson.usecase.GetLessonByIdUseCase
import ru.miller.bot.domain.lesson.usecase.GetLessonByIdUseCaseImpl
import ru.miller.bot.domain.lesson.usecase.UpdateLessonByIdUseCase
import ru.miller.bot.domain.lesson.usecase.UpdateLessonByIdUseCaseImpl
import ru.miller.bot.domain.user.usecase.CreateUserUseCase
import ru.miller.bot.domain.user.usecase.CreateUserUseCaseImpl
import ru.miller.bot.domain.user.usecase.DeleteUserByIdUseCase
import ru.miller.bot.domain.user.usecase.DeleteUserByIdUseCaseImpl
import ru.miller.bot.domain.user.usecase.GetUserByIdUseCase
import ru.miller.bot.domain.user.usecase.GetUserByIdUseCaseImpl
import ru.miller.bot.domain.user.usecase.GetUserByTelegramIdUseCase
import ru.miller.bot.domain.user.usecase.GetUserByTelegramIdUseCaseImpl
import ru.miller.bot.domain.user.usecase.GetUsersUseCase
import ru.miller.bot.domain.user.usecase.GetUsersUseCaseImpl
import ru.miller.bot.domain.user.usecase.UpdateUserByIdUseCase
import ru.miller.bot.domain.user.usecase.UpdateUserByIdUseCaseImpl

val useCaseModule = module {

    // region User
    factoryOf(::CreateUserUseCaseImpl) bind CreateUserUseCase::class
    factoryOf(::DeleteUserByIdUseCaseImpl) bind DeleteUserByIdUseCase::class
    factoryOf(::GetUserByIdUseCaseImpl) bind GetUserByIdUseCase::class
    factoryOf(::GetUserByTelegramIdUseCaseImpl) bind GetUserByTelegramIdUseCase::class
    factoryOf(::GetUsersUseCaseImpl) bind GetUsersUseCase::class
    factoryOf(::UpdateUserByIdUseCaseImpl) bind UpdateUserByIdUseCase::class
    // endregion

    // region Lesson
    factoryOf(::CreateLessonUseCaseImpl) bind CreateLessonUseCase::class
    factoryOf(::DeleteLessonByIdUseCaseImpl) bind DeleteLessonByIdUseCase::class
    factoryOf(::GetLessonByIdUseCaseImpl) bind GetLessonByIdUseCase::class
    factoryOf(::GetLessonByDateUseCaseImpl) bind GetLessonByDateUseCase::class
    factoryOf(::UpdateLessonByIdUseCaseImpl) bind UpdateLessonByIdUseCase::class
    // endregion
}
