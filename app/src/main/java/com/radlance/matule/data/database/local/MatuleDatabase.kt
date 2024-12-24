package com.radlance.matule.data.database.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.radlance.matule.data.database.local.entity.SearchHistoryQueryEntity

@Database(entities = [SearchHistoryQueryEntity::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
abstract class MatuleDatabase : RoomDatabase() {
    abstract fun getMatuleDao(): MatuleDao

    companion object {
        @Volatile
        private var INSTANCE: MatuleDatabase? = null

        fun getInstance(applicationContext: Context): MatuleDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    applicationContext, MatuleDatabase::class.java, "matule_db"
                ).build()
            }.also {
                INSTANCE = it
            }
        }
    }
}