package com.radlance.matule.data.database.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "product",
    foreignKeys = [
        ForeignKey(
            entity = LocalCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"]
        )
    ]
)
data class LocalProductEntity(
    val title: String,
    val price: Double,
    val description: String,
    @ColumnInfo("image_url") val imageUrl: String,
    @ColumnInfo("category_id", index = true) val categoryId: Int,
    @ColumnInfo("is_favorite") val isFavorite: Boolean = false,
    @ColumnInfo("quantity_in_cart") val quantityInCart: Int = 0,
    @PrimaryKey val id: Int = 0
)
