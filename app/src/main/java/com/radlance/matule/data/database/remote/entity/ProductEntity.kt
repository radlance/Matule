package com.radlance.matule.data.database.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductEntity(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("category_id") val categoryId: Int,
)
