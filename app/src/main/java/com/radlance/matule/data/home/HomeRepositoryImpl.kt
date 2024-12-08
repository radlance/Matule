package com.radlance.matule.data.home

import com.radlance.matule.data.common.Mapper
import com.radlance.matule.data.database.remote.entity.CartEntity
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
                        products = products.map {
                            it.toProduct(
                                isFavorite = isFavoriteProduct(productId = it.id),
                                quantityInCart = getProductQuantityInCart(productId = it.id)
                            )
                        }
                    )
                )

        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }

    override suspend fun changeFavoriteStatus(productId: Int): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            user?.let {
                if (isFavoriteProduct(productId)) {
                    supabaseClient.from("favorite").delete {
                        filter {
                            FavoriteEntity::productId eq productId
                            FavoriteEntity::userId eq user.id
                        }
                    }
                } else {
                    supabaseClient.from("favorite").insert(
                        FavoriteEntity(productId = productId, userId = user.id)
                    )
                }
            }
            FetchResult.Success(productId)
        } catch (e: Exception) {
            FetchResult.Error(productId)
        }
    }

    override suspend fun addProductToCart(productId: Int): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            user?.let {
                if (getProductQuantityInCart(productId) == 0) {
                    supabaseClient.from("cart").insert(
                        CartEntity(productId = productId, userId = user.id, quantity = 1)
                    )
                }
            }
            FetchResult.Success(productId)
        } catch (e: Exception) {
            FetchResult.Error(productId)
        }
    }

    override suspend fun updateQuantity(productId: Int, quantity: Int): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            user?.let {
                supabaseClient.from("cart").update(
                    {
                        CartEntity::quantity setTo quantity
                    }
                ) {
                    filter {
                        CartEntity::productId eq productId
                        CartEntity::userId eq user.id
                    }
                }
            }
            FetchResult.Success(productId)
        } catch (e: Exception) {
            FetchResult.Error(productId)
        }
    }

    override suspend fun removeProductFromCart(productId: Int): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            user?.let {
                supabaseClient.from("cart").delete {
                    filter {
                        CartEntity::productId eq productId
                        CartEntity::userId eq user.id
                    }
                }
            }
            FetchResult.Success(productId)
        }  catch (e: Exception) {
            FetchResult.Error(productId)
        }
    }

    private suspend fun getProductQuantityInCart(productId: Int): Int {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            user?.let {
                val cartEntities = supabaseClient.from("cart").select {
                    filter {
                        CartEntity::productId eq productId
                        CartEntity::userId eq user.id
                    }
                }.decodeList<CartEntity>()
                cartEntities.first().quantity
            } ?: -1

        } catch (e: Exception) {
            0
        }
    }

    private suspend fun isFavoriteProduct(productId: Int): Boolean {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            user?.let {
                val favorites = supabaseClient.from("favorite").select {
                    filter {
                        FavoriteEntity::productId eq productId
                        FavoriteEntity::userId eq it.id
                    }
                }.decodeList<FavoriteEntity>()
                favorites.size == 1
            } ?: false

        } catch (e: Exception) {
            false
        }
    }
}
