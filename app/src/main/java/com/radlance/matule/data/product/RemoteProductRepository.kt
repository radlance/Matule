package com.radlance.matule.data.product

import com.radlance.matule.data.database.local.MatuleDao
import com.radlance.matule.data.database.remote.RemoteMapper
import com.radlance.matule.data.database.remote.entity.CartEntity
import com.radlance.matule.data.database.remote.entity.FavoriteEntity
import com.radlance.matule.data.database.remote.entity.HistoryEntity
import com.radlance.matule.data.database.remote.entity.RemoteCategoryEntity
import com.radlance.matule.data.database.remote.entity.RemoteProductEntity
import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.domain.product.CatalogFetchContent
import com.radlance.matule.domain.product.Product
import com.radlance.matule.domain.product.ProductRepository
import com.radlance.matule.domain.remote.FetchResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class RemoteProductRepository @Inject constructor(
    private val supabaseClient: SupabaseClient,
    private val dao: MatuleDao
) : ProductRepository, RemoteMapper() {
    override suspend fun fetchCatalogContent(): FetchResult<CatalogFetchContent> {
        val user =
            supabaseClient.auth.currentSessionOrNull()?.user ?: return FetchResult.Error(null)
        return try {
            val localCartEntities = dao.getCartProducts()
            if (localCartEntities.isNotEmpty()) {
                val userProducts = supabaseClient.from("cart").select {
                    filter {
                        CartEntity::userId eq user.id
                    }
                }.decodeList<CartEntity>()

                if (userProducts.isEmpty()) {
                    localCartEntities.forEach { entity ->
                        val product = supabaseClient.from("product").select {
                            filter {
                                RemoteProductEntity::title eq entity.title
                            }
                        }.decodeList<RemoteProductEntity>().first()

                        supabaseClient.from("cart").insert(
                            CartEntity(
                                productId = product.id,
                                quantity = entity.quantityInCart,
                                userId = user.id
                            )
                        )
                    }
                    dao.clearCart()
                }
            }

            val categories = supabaseClient.from("category").select().decodeList<RemoteCategoryEntity>()
            val products = supabaseClient.from("product").select().decodeList<RemoteProductEntity>()

            val favoriteProducts = supabaseClient.from("favorite")
                .select {
                    filter { FavoriteEntity::userId eq user.id }
                }

                .decodeList<FavoriteEntity>()
                .associateBy { it.productId }

            val cartProducts = supabaseClient.from("cart")
                .select {
                    filter { CartEntity::userId eq user.id }
                }
                .decodeList<CartEntity>()
                .associateBy { it.productId }

            FetchResult.Success(
                CatalogFetchContent(
                    categories = categories.map { it.toCategory() },
                    products = products.map { product ->
                        product.toProduct(
                            isFavorite = favoriteProducts.containsKey(product.id),
                            quantityInCart = cartProducts[product.id]?.quantity ?: 0
                        )
                    }
                )
            )

        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }

    override suspend fun changeFavoriteStatus(productId: Int): FetchResult<Int> {
        val user =
            supabaseClient.auth.currentSessionOrNull()?.user ?: return FetchResult.Error(productId)

        return try {
            val favorites = supabaseClient.from("favorite")
                .select {
                    filter {
                        FavoriteEntity::productId eq productId
                        FavoriteEntity::userId eq user.id
                    }
                }
                .decodeList<FavoriteEntity>()

            if (favorites.isNotEmpty()) {
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

            FetchResult.Success(productId)
        } catch (e: Exception) {
            FetchResult.Error(productId)
        }
    }

    override suspend fun addProductToCart(productId: Int): FetchResult<Int> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            user?.let {
                val cartEntity = CartEntity(productId = productId, userId = user.id, quantity = 1)
                supabaseClient.from("cart").upsert(cartEntity)
            } ?: FetchResult.Error(productId)
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

    override suspend fun placeOrder(products: List<Product>): FetchResult<List<Product>> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            user?.let {
                products.forEach { product ->
                    val historyEntity = product.toHistoryEntity(user.id)

                    supabaseClient.from("history").insert(historyEntity)
                    supabaseClient.from("cart").delete {
                        filter {
                            CartEntity::productId eq product.id
                            CartEntity::userId eq user.id
                        }
                    }
                }
            }
            FetchResult.Success(products)

        } catch (e: Exception) {
            FetchResult.Error(emptyList())
        }
    }

    override suspend fun loadHistory(): FetchResult<List<HistoryProduct>> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            if (user != null) {
                val historyEntities =
                    supabaseClient.from("history").select {
                        filter {
                            HistoryEntity::userId eq user.id
                        }
                    }.decodeList<HistoryEntity>()
                val products = supabaseClient.from("product").select().decodeList<RemoteProductEntity>()

                FetchResult.Success(
                    historyEntities.map { historyEntity ->
                        historyEntity.toHistoryProduct(
                            products.first { productEntity ->
                                productEntity.id == historyEntity.productId
                            }
                        )
                    }
                )
            } else {
                FetchResult.Error(emptyList())
            }
        } catch (e: Exception) {
            FetchResult.Error(emptyList())
        }
    }
}
