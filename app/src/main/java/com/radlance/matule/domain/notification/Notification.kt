package com.radlance.matule.domain.notification

import kotlinx.datetime.LocalDateTime

data class Notification(
    val title: String,
    val content: String,
    val sendDate: LocalDateTime,
    val id: Int = 0
)
