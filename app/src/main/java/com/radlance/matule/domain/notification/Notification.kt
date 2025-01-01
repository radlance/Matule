package com.radlance.matule.domain.notification

import kotlinx.datetime.LocalDateTime

data class Notification(
    val title: String,
    val message: String,
    val sendDate: LocalDateTime,
    val isRead: Boolean,
    val id: Int = 0
)
