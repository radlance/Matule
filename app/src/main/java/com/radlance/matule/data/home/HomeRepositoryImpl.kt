package com.radlance.matule.data.home

import com.radlance.matule.data.common.Mapper
import com.radlance.matule.data.database.remote.entity.CategoryEntity
import com.radlance.matule.data.database.remote.entity.FavoriteEntity
import com.radlance.matule.data.database.remote.entity.ProductEntity
import com.radlance.matule.domain.home.CatalogFetchContent
import com.radlance.matule.domain.home.HomeRepository
import com.radlance.matule.domain.remote.FetchResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val supabaseClient: SupabaseClient) :
    HomeRepository, Mapper() {
    override suspend fun fetchCatalogContent(): FetchResult<CatalogFetchContent> {
        return try {
            val categories = supabaseClient.from("category").select().decodeList<CategoryEntity>()

            val products = supabaseClient.from("product").select().decodeList<ProductEntity>()

                FetchResult.Success(
                    CatalogFetchContent(
                        categories = categories.map { it.toCategory() },
                        products = products.map { it.toProduct(it.isFavoriteProduct()) }
                    )
                )

        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }

    override suspend fun addToFavorites(productId: Int): FetchResult<Unit> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            user?.let {
                supabaseClient.from("favorite").insert(
                    FavoriteEntity(productId = productId, userId = it.id)
                )
            }
            FetchResult.Success(Unit)
        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }

    private suspend fun ProductEntity.isFavoriteProduct(): Boolean {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            if (user != null) {
                val favorites = supabaseClient.from("favorite").select {
                    filter {
                        FavoriteEntity::productId eq this@isFavoriteProduct.id
                        FavoriteEntity::userId eq user.id
                    }
                }.decodeList<FavoriteEntity>()
                favorites.isNotEmpty()
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}
