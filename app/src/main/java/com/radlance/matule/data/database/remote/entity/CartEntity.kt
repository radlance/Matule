package com.radlance.matule.data.database.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartEntity(
    @SerialName("product_id") val productId: Int,
    val quantity: Int,
    @SerialName("user_id") val userId: String
)
