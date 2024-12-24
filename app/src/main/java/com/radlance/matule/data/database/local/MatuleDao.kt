package com.radlance.matule.data.database.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.radlance.matule.data.database.local.entity.SearchHistoryQueryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatuleDao {
    @Query("SELECT * FROM search_history_query ORDER BY query_time DESC LIMIT 6")
    fun getHistory(): Flow<List<SearchHistoryQueryEntity>>

    @Insert
    suspend fun insertSearchHistoryQuery(searchHistoryQuery: SearchHistoryQueryEntity)

    @Query(
        """
        DELETE FROM search_history_query 
        WHERE id = (SELECT id FROM search_history_query ORDER BY query_time LIMIT 1)
    """
    )
    suspend fun removeOldestHistory()
}