package com.radlance.matule.data.common

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth

abstract class BaseRepository<T : Repository>(
    private val supabaseClient: SupabaseClient,
    private val localProductRepository: T,
    private val remoteProductRepository: T
) {
    protected fun getRepository(): T {
        return if (supabaseClient.auth.currentSessionOrNull()?.user == null) {
            localProductRepository
        } else {
            remoteProductRepository
        }
    }
}