package com.radlance.matule.data.product

import com.radlance.matule.domain.product.ProductRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth

abstract class BaseProductRepository(
    private val supabaseClient: SupabaseClient,
    private val localProductRepository: ProductRepository,
    private val remoteProductRepository: ProductRepository
) : ProductRepository {
    protected fun getRepository(): ProductRepository {
        return if (supabaseClient.auth.currentSessionOrNull()?.user == null) {
            localProductRepository
        } else {
            remoteProductRepository
        }
    }
}