package ru.miller.bot.app.modules

object KoinModules {

    val all = listOf(
        envVariablesModule,
        coroutineModule,
        databaseModule,
        datasourceModule,
        useCaseModule,
        controllerModule,
    )
}
