package ru.miller.bot.app.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.miller.bot.app.api.lesson.controller.LessonController
import ru.miller.bot.app.api.user.controller.UserController

val controllerModule = module {
    factoryOf(::UserController)
    factoryOf(::LessonController)
}
