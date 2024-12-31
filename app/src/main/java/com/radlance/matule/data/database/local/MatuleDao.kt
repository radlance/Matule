package com.radlance.matule.data.database.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.radlance.matule.data.database.local.entity.LocalCategoryEntity
import com.radlance.matule.data.database.local.entity.LocalProductEntity
import com.radlance.matule.data.database.local.entity.SearchHistoryQueryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatuleDao {
    @Query("SELECT * FROM search_history_query ORDER BY query_time DESC LIMIT 6")
    fun getHistory(): Flow<List<SearchHistoryQueryEntity>>

    @Query("SELECT * FROM search_history_query WHERE `query` = :query LIMIT 1")
    suspend fun getSearchHistoryQuery(query: String): SearchHistoryQueryEntity?

    @Insert
    suspend fun insertSearchHistoryQuery(searchHistoryQuery: SearchHistoryQueryEntity)

    @Update
    suspend fun updateSearchHistoryQuery(searchHistoryQuery: SearchHistoryQueryEntity)

    @Query(
        """
        DELETE FROM search_history_query 
        WHERE id = (SELECT id FROM search_history_query ORDER BY query_time LIMIT 1)
    """
    )
    suspend fun removeOldestHistory()

    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<LocalCategoryEntity>

    @Query("SELECT * FROM product")
    suspend fun getProducts(): List<LocalProductEntity>

    @Query("UPDATE product SET is_favorite = NOT is_favorite WHERE id = :productId")
    suspend fun changeFavoriteStatus(productId: Int)

    @Query("UPDATE product SET quantity_in_cart = 1 WHERE id = :productId")
    suspend fun addProductToCart(productId: Int)

    @Query("UPDATE product SET quantity_in_cart = :quantity WHERE id = :productId")
    suspend fun updateProductQuantity(productId: Int, quantity: Int)

    @Query("DELETE FROM product WHERE id = :productId")
    suspend fun removeProductFromCart(productId: Int)
}