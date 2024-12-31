package com.radlance.matule.data.database.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class LocalCategoryEntity(
    val title: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
