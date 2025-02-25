package ru.bezdar.skip.data.database.user.entity

import ru.bezdar.skip.domain.user.model.UserRole

enum class UserRoleDb {
    STUDENT,
    TEACHER,
    DEAN,
    ADMIN,
}

fun UserRoleDb.toDomain() = when (this) {
    UserRoleDb.STUDENT -> UserRole.STUDENT
    UserRoleDb.TEACHER -> UserRole.TEACHER
    UserRoleDb.DEAN -> UserRole.DEAN
    UserRoleDb.ADMIN -> UserRole.ADMIN
}

fun UserRole.toDb() = when (this) {
    UserRole.STUDENT -> UserRoleDb.STUDENT
    UserRole.TEACHER -> UserRoleDb.TEACHER
    UserRole.DEAN -> UserRoleDb.DEAN
    UserRole.ADMIN -> UserRoleDb.ADMIN
}
