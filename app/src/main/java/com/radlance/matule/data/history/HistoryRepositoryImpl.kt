package com.radlance.matule.data.history

import com.radlance.matule.data.database.remote.RemoteMapper
import com.radlance.matule.data.database.remote.entity.HistoryEntity
import com.radlance.matule.data.database.remote.entity.RemoteProductEntity
import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.domain.history.HistoryRepository
import com.radlance.matule.domain.remote.FetchResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : HistoryRepository, RemoteMapper() {
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