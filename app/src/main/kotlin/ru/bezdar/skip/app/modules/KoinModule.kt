package ru.bezdar.skip.app.modules

object KoinModules {

    val all = listOf(
        envVariablesModule,
        coroutineModule,
        databaseModule,
        datasourceModule,
    )
}
