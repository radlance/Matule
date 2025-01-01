package com.radlance.matule.data.database.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "notification")
data class LocalNotificationEntity(
    val title: String,
    val message: String,
    @ColumnInfo(name = "is_read") val isRead: Boolean,
    @ColumnInfo(name = "send_date") val sendDate: LocalDateTime,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)