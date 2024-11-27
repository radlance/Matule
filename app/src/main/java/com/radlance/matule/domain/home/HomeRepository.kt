package com.radlance.matule.domain.home

import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getCategories(): Flow<List<Category>>
    fun getProducts(): Flow<List<Product>>

    suspend fun addCategories(categories: List<Category>)
    suspend fun addProducts(products: List<Product>)
    suspend fun switchFavoriteStatus(productId: Int)
}
