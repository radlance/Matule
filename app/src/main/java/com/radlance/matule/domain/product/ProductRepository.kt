package com.radlance.matule.domain.product

import com.radlance.matule.data.common.Repository
import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.domain.remote.FetchResult

interface ProductRepository : Repository {
    suspend fun fetchCatalogContent(): FetchResult<CatalogFetchContent>
    suspend fun changeFavoriteStatus(productId: Int): FetchResult<Int>
    suspend fun addProductToCart(productId: Int): FetchResult<Int>
    suspend fun updateQuantity(productId: Int, quantity: Int): FetchResult<Int>
    suspend fun removeProductFromCart(productId: Int): FetchResult<Int>
    suspend fun placeOrder(products: List<Product>): FetchResult<List<Product>>
    suspend fun loadHistory(): FetchResult<List<HistoryProduct>>
}
