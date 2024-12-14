package com.radlance.matule.domain.history

import kotlinx.datetime.Instant

data class HistoryProduct(
    val id: Int,
    val title: String,
    val price: Double,
    val imageUrl: String,
    val orderTime: Instant
)
