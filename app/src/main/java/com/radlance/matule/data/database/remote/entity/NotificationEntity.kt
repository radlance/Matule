package com.radlance.matule.data.database.remote.entity

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationEntity(
    @SerialName("user_id") val userId: String,
    val title: String,
    val message: String,
    @SerialName("is_read") val isRead: Boolean,
    @SerialName("send_date") val sendDate: LocalDateTime,
    val id: Int = 0
)
