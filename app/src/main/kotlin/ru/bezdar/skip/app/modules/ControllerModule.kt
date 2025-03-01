package ru.bezdar.skip.app.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.bezdar.skip.app.api.group.controller.GroupController
import ru.bezdar.skip.app.api.request.controller.RequestController
import ru.bezdar.skip.app.api.user.controller.UserController

val controllerModule = module {
    factoryOf(::RequestController)
    factoryOf(::GroupController)
    factoryOf(::UserController)
}
