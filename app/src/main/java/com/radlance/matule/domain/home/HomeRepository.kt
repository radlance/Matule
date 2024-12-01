package com.radlance.matule.domain.home

import com.radlance.matule.domain.remote.FetchResult

interface HomeRepository {
    suspend fun fetchCatalogContent(): FetchResult<CatalogFetchContent>
    suspend fun changeFavoriteStatus(productId: Int): FetchResult<Unit>
    suspend fun removeFromFavorite(productId: Int): FetchResult<Unit>
}
