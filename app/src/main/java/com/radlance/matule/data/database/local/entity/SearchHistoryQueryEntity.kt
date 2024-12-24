package com.radlance.matule.data.database.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "search_history_query")
data class SearchHistoryQueryEntity(
    val query: String,
    @ColumnInfo(name = "query_time") val queryTime: LocalDateTime,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
