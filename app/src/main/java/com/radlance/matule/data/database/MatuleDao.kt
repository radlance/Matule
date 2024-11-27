package com.radlance.matule.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.radlance.matule.data.database.entity.CategoryEntity
import com.radlance.matule.data.database.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatuleDao {
    @Query("SELECT * FROM category")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM product")
    fun getProducts(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Query("UPDATE product SET is_favorite = NOT is_favorite WHERE id = :productId")
    suspend fun switchFavoriteStatus(productId: Int)
}
