package com.radlance.matule.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.radlance.matule.data.database.entity.CategoryEntity
import com.radlance.matule.data.database.entity.ProductEntity

@Database(
    entities = [CategoryEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MatuleDatabase : RoomDatabase() {
    abstract fun dao(): MatuleDao

    companion object {
        @Volatile
        private var INSTANCE: MatuleDatabase? = null

        fun getInstance(applicationContext: Context): MatuleDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    applicationContext,
                    MatuleDatabase::class.java,
                    "matule_db"
                ).build()
            }.also {
                INSTANCE = it
            }
        }
    }
}