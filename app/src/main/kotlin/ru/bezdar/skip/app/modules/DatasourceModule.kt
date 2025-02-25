package ru.bezdar.skip.app.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bezdar.skip.data.database.request.RequestDbDataSourceImpl
import ru.bezdar.skip.data.database.user.UserDbDataSourceImpl
import ru.bezdar.skip.domain.request.RequestDbDataSource
import ru.bezdar.skip.domain.user.UserDbDataSource

val datasourceModule = module {
    // region Database
    factoryOf(::RequestDbDataSourceImpl) bind RequestDbDataSource::class
    factoryOf(::UserDbDataSourceImpl) bind UserDbDataSource::class
    // endregion
}
