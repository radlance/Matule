package com.radlance.matule.data.database.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.radlance.matule.data.database.local.entity.LocalCategoryEntity
import com.radlance.matule.data.database.local.entity.LocalProductEntity
import com.radlance.matule.data.database.local.entity.SearchHistoryQueryEntity

@Database(
    entities = [
        SearchHistoryQueryEntity::class,
        LocalCategoryEntity::class,
        LocalProductEntity::class
    ],
    version = 1,
    exportSchema = false
)
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
                ).createFromAsset("matule.db").build()
            }.also {
                INSTANCE = it
            }
        }
    }
}