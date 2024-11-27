package com.radlance.matule.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "product",
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["category_id"]
    )]
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val price: Double,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "category_id", index = true) val categoryId: Int
)
