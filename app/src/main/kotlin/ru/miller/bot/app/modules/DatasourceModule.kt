package ru.miller.bot.app.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.miller.bot.data.database.lesson.LessonDbDataSourceImpl
import ru.miller.bot.data.database.studyPlan.StudyPlanDbDataSourceImpl
import ru.miller.bot.data.database.user.UserDbDataSourceImpl
import ru.miller.bot.domain.lesson.LessonDbDataSource
import ru.miller.bot.domain.studyPlan.StudyPlanDbDataSource
import ru.miller.bot.domain.user.UserDbDataSource

val datasourceModule = module {
    // region Database
    factoryOf(::UserDbDataSourceImpl) bind UserDbDataSource::class
    factoryOf(::StudyPlanDbDataSourceImpl) bind StudyPlanDbDataSource::class
    factoryOf(::LessonDbDataSourceImpl) bind LessonDbDataSource::class
    // endregion
}
