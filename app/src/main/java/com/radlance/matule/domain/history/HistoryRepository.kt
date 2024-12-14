package com.radlance.matule.domain.history

import com.radlance.matule.domain.remote.FetchResult

interface HistoryRepository {
    suspend fun loadHistory(): FetchResult<List<HistoryProduct>>
}