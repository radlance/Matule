package com.radlance.matule.data.home

import com.radlance.matule.data.common.Mapper
import com.radlance.matule.data.database.MatuleDao
import com.radlance.matule.domain.home.Category
import com.radlance.matule.domain.home.HomeRepository
import com.radlance.matule.domain.home.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val dao: MatuleDao) : HomeRepository, Mapper() {
    override fun getCategories(): Flow<List<Category>> {
        return dao.getCategories().map { it.map { categoryEntity -> categoryEntity.toCategory() } }
    }

    override fun getProducts(): Flow<List<Product>> {
        return dao.getProducts().map { it.map { productEntity -> productEntity.toProduct() } }
    }

    override suspend fun addCategories(categories: List<Category>) {
        dao.insertCategories(categories.map { it.toCategoryEntity() })
    }

    override suspend fun addProducts(products: List<Product>) {
        dao.insertProducts(products.map { it.toProductEntity() })
    }

    override suspend fun switchFavoriteStatus(productId: Int) {
        dao.switchFavoriteStatus(productId)
    }
}
