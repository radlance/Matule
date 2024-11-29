package com.radlance.matule.domain.home

import com.radlance.matule.domain.remote.FetchResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getCategories(): Flow<FetchResult<List<Category>>>
    fun getProducts(): Flow<FetchResult<List<Product>>>

    suspend fun addCategories(categories: List<Category>)
    suspend fun addProducts(products: List<Product>)
    suspend fun switchFavoriteStatus(productId: Int)
}
