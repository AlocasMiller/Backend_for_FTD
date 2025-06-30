package ru.miller.bot.app.modules

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.miller.bot.data.database.common.DatabaseProvider

val databaseModule = module {
    singleOf(DatabaseProvider::getDataSource)
    singleOf(DatabaseProvider::getDatabase)
}
