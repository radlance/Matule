package com.radlance.matule.domain.home

import com.radlance.matule.domain.remote.FetchResult

interface HomeRepository {
    suspend fun fetchCatalogContent(): FetchResult<CatalogFetchContent>

    suspend fun addCategories(categories: List<Category>)
    suspend fun addProducts(products: List<Product>)
    suspend fun switchFavoriteStatus(productId: Int)
}
