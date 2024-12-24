package com.radlance.matule.data.search

import com.radlance.matule.data.database.local.LocalMapper
import com.radlance.matule.data.database.local.MatuleDao
import com.radlance.matule.domain.search.ProductSearchRepository
import com.radlance.matule.domain.search.SearchHistoryQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductSearchRepositoryImpl @Inject constructor(
    private val dao: MatuleDao
) : ProductSearchRepository, LocalMapper() {
    override fun loadSearchHistory(): Flow<List<SearchHistoryQuery>> {
        return dao.getHistory().map { historyEntities ->
            historyEntities.map { it.toSearchHistoryQuery() }
        }
    }

    override suspend fun addQueryToHistory(searchHistoryQuery: SearchHistoryQuery) {
        val existingQuery = dao.getSearchHistoryQuery(searchHistoryQuery.query)
        if (existingQuery != null) {
            val updatedQuery = existingQuery.copy(queryTime = searchHistoryQuery.queryTime)
            dao.updateSearchHistoryQuery(updatedQuery)
        } else {
            if(dao.getHistory().first().size >= 6) {
                dao.removeOldestHistory()
            }
            dao.insertSearchHistoryQuery(searchHistoryQuery.toSearchHistoryQueryEntity())
        }
    }
}