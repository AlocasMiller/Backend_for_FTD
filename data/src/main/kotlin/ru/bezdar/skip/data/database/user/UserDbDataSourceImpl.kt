package ru.bezdar.skip.data.database.user

import org.jetbrains.exposed.sql.Database
import ru.bezdar.skip.data.database.common.DatabaseDataSourse
import ru.bezdar.skip.domain.user.UserDbDataSource

class UserDbDataSourceImpl(override val database: Database) : UserDbDataSource, DatabaseDataSourse {
}
