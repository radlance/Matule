package com.radlance.matule.data.database.remote.entity

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryEntity(
    val id: Int,
    @SerialName("user_id") val userId: String,
    @SerialName("product_id") val productId: Int,
    val quantity: Int,
    @SerialName("order_date") val orderDate: Instant
)