package ru.miller.bot.domain.common.model

import java.util.UUID

@JvmInline
value class Id<out T>(val value: UUID)
