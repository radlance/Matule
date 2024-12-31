package com.radlance.matule.data.product

import com.radlance.matule.data.database.local.LocalMapper
import com.radlance.matule.data.database.local.MatuleDao
import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.domain.product.CatalogFetchContent
import com.radlance.matule.domain.product.Product
import com.radlance.matule.domain.product.ProductRepository
import com.radlance.matule.domain.remote.FetchResult
import javax.inject.Inject

class LocalProductRepository @Inject constructor(
    private val dao: MatuleDao
) : ProductRepository, LocalMapper() {
    override suspend fun fetchCatalogContent(): FetchResult<CatalogFetchContent> {
        val catalogFetchContent = CatalogFetchContent(
            categories = dao.getCategories().map { categoryEntity -> categoryEntity.toCategory() },
            products = dao.getProducts().map { productEntity -> productEntity.toProduct() }
        )
        return FetchResult.Success(catalogFetchContent)
    }

    override suspend fun changeFavoriteStatus(productId: Int): FetchResult<Int> {
        dao.changeFavoriteStatus(productId)
        return FetchResult.Success(productId)
    }

    override suspend fun addProductToCart(productId: Int): FetchResult<Int> {
        dao.addProductToCart(productId)
        return FetchResult.Success(productId)
    }

    override suspend fun updateQuantity(productId: Int, quantity: Int): FetchResult<Int> {
        dao.updateProductQuantity(productId, quantity)
        return FetchResult.Success(productId)
    }

    override suspend fun removeProductFromCart(productId: Int): FetchResult<Int> {
        dao.removeProductFromCart(productId)
        return FetchResult.Success(productId)
    }

    override suspend fun placeOrder(products: List<Product>): FetchResult<List<Product>> {
        return FetchResult.Error(emptyList())
    }

    override suspend fun loadHistory(): FetchResult<List<HistoryProduct>> {
        return FetchResult.Error(emptyList())
    }
}