package com.radlance.matule.di

import com.radlance.matule.data.database.local.MatuleDao
import com.radlance.matule.data.product.LocalProductRepository
import com.radlance.matule.data.product.ProductRepositoryImpl
import com.radlance.matule.data.product.RemoteProductRepository
import com.radlance.matule.domain.product.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductModule {
    @Provides
    @Singleton
    fun provideLocalProductRepository(dao: MatuleDao): LocalProductRepository {
        return LocalProductRepository(dao)
    }

    @Provides
    @Singleton
    fun provideRemoteProductRepository(
        supabaseClient: SupabaseClient,
        dao: MatuleDao
    ): RemoteProductRepository {
        return RemoteProductRepository(supabaseClient, dao)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        localRepository: LocalProductRepository,
        remoteRepository: RemoteProductRepository,
        supabaseClient: SupabaseClient
    ): ProductRepository {
        return ProductRepositoryImpl(supabaseClient, localRepository, remoteRepository)
    }
}