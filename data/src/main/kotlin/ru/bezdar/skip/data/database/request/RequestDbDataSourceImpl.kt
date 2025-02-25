package ru.bezdar.skip.data.database.request

import org.jetbrains.exposed.sql.Database
import ru.bezdar.skip.data.database.common.DatabaseDataSourse
import ru.bezdar.skip.domain.request.RequestDbDataSource

class RequestDbDataSourceImpl(override val database: Database) : RequestDbDataSource, DatabaseDataSourse {
}
