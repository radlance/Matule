package com.radlance.matule.domain.home

data class Product(
    val title: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val categoryId: Int,
    val isFavorite: Boolean,
    val inCart: Boolean,
    val id: Int = 0,
)