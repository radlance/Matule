package com.radlance.matule.data.database.local

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun toDate(dateTime: String): LocalDateTime {
        return LocalDateTime.parse(dateTime)
    }

    @TypeConverter
    fun toDateString(dateTime: LocalDateTime): String {
        return dateTime.toString()
    }
}