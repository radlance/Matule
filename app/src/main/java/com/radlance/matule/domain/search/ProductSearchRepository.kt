package com.radlance.matule.domain.search

import kotlinx.coroutines.flow.Flow

interface ProductSearchRepository {
    fun loadSearchHistory(): Flow<List<SearchHistoryQuery>>
    suspend fun addQueryToHistory(searchHistoryQuery: SearchHistoryQuery)
}