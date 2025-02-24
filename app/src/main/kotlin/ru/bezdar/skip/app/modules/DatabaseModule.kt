package ru.bezdar.skip.app.modules

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bezdar.skip.data.database.common.DatabaseProvider

val databaseModule = module {
    singleOf(DatabaseProvider::getDataSource)
    singleOf(DatabaseProvider::getDatabase)
}
