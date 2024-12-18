package com.radlance.matule.domain.product

data class Product(
    val title: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val categoryId: Int,
    val isFavorite: Boolean,
    val quantityInCart: Int,
    val id: Int = 0,
)