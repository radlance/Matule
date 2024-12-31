package com.radlance.matule.data.product

import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.domain.product.CatalogFetchContent
import com.radlance.matule.domain.product.Product
import com.radlance.matule.domain.product.ProductRepository
import com.radlance.matule.domain.remote.FetchResult
import io.github.jan.supabase.SupabaseClient
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    supabaseClient: SupabaseClient,
    localProductRepository: ProductRepository,
    remoteProductRepository: ProductRepository
) : BaseProductRepository(supabaseClient, localProductRepository, remoteProductRepository) {
    override suspend fun fetchCatalogContent(): FetchResult<CatalogFetchContent> {
        return getRepository().fetchCatalogContent()
    }

    override suspend fun changeFavoriteStatus(productId: Int): FetchResult<Int> {
        return getRepository().changeFavoriteStatus(productId)
    }

    override suspend fun addProductToCart(productId: Int): FetchResult<Int> {
        return getRepository().addProductToCart(productId)
    }

    override suspend fun updateQuantity(productId: Int, quantity: Int): FetchResult<Int> {
        return getRepository().updateQuantity(productId, quantity)
    }

    override suspend fun removeProductFromCart(productId: Int): FetchResult<Int> {
        return getRepository().removeProductFromCart(productId)
    }

    override suspend fun placeOrder(products: List<Product>): FetchResult<List<Product>> {
        return getRepository().placeOrder(products)
    }

    override suspend fun loadHistory(): FetchResult<List<HistoryProduct>> {
        return getRepository().loadHistory()
    }
}