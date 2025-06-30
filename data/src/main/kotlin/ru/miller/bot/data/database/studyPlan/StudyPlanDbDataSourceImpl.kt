package ru.miller.bot.data.database.studyPlan

import org.jetbrains.exposed.sql.Database
import ru.miller.bot.data.database.common.DatabaseDataSourse
import ru.miller.bot.domain.studyPlan.StudyPlanDbDataSource

class StudyPlanDbDataSourceImpl(override val database: Database) : StudyPlanDbDataSource, DatabaseDataSourse {

}
