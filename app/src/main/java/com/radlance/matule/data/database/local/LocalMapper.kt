package com.radlance.matule.data.database.local

import com.radlance.matule.data.database.local.entity.SearchHistoryQueryEntity
import com.radlance.matule.domain.search.SearchHistoryQuery

abstract class LocalMapper {
    protected fun SearchHistoryQueryEntity.toSearchHistoryQuery(): SearchHistoryQuery {
        return SearchHistoryQuery(query = query, queryTime = queryTime, id = id)
    }

    protected fun SearchHistoryQuery.toSearchHistoryQueryEntity(): SearchHistoryQueryEntity {
        return SearchHistoryQueryEntity(query = query, queryTime = queryTime)
    }
}