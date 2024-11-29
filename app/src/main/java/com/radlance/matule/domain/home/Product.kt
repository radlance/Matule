package com.radlance.matule.domain.home

data class Product(
    val title: String,
    val price: Double,
    val imageUrl: String,
    val categoryId: Int,
    val id: Int = 0,
)