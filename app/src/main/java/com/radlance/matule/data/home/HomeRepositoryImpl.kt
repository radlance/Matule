package com.radlance.matule.data.home

import com.radlance.matule.data.common.Mapper
import com.radlance.matule.data.database.remote.entity.CategoryEntity
import com.radlance.matule.data.database.remote.entity.ProductEntity
import com.radlance.matule.domain.home.Category
import com.radlance.matule.domain.home.HomeRepository
import com.radlance.matule.domain.home.Product
import com.radlance.matule.domain.remote.FetchResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val supabaseClient: SupabaseClient) :
    HomeRepository, Mapper() {
    override fun getCategories(): Flow<FetchResult<List<Category>>> {
        return flow<FetchResult<List<Category>>> {
            val categories = supabaseClient.from("category").select().decodeList<CategoryEntity>()
            emit(FetchResult.Success(categories.map { categoryEntity -> categoryEntity.toCategory() }))
        }.catch {
            emit(FetchResult.Error(null))
        }
    }

    override fun getProducts(): Flow<FetchResult<List<Product>>> {
        return flow<FetchResult<List<Product>>> {
            val categories = supabaseClient.from("product").select().decodeList<ProductEntity>()
            emit(FetchResult.Success(categories.map { productEntity -> productEntity.toProduct() }))
        }.catch {
            emit(FetchResult.Error(null))
        }
    }

    override suspend fun addCategories(categories: List<Category>) {
    }

    override suspend fun addProducts(products: List<Product>) {
    }

    override suspend fun switchFavoriteStatus(productId: Int) {
    }
}
