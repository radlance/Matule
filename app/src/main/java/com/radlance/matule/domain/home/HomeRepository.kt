package com.radlance.matule.domain.home

import com.radlance.matule.domain.remote.FetchResult

interface HomeRepository {
    suspend fun fetchCatalogContent(): FetchResult<CatalogFetchContent>
    suspend fun changeFavoriteStatus(productId: Int): FetchResult<Int>
    suspend fun addProductToCart(productId: Int): FetchResult<Int>
    suspend fun updateQuantity(productId: Int, quantity: Int): FetchResult<Int>
    suspend fun removeProductFromCart(productId: Int): FetchResult<Int>
    suspend fun placeOrder(products: List<Product>): FetchResult<Unit>
}
