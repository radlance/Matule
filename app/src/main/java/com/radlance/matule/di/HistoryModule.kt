package com.radlance.matule.di

import com.radlance.matule.data.search.ProductSearchRepositoryImpl
import com.radlance.matule.domain.search.ProductSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HistoryModule {
    @Binds
    fun provideRepository(productSearchRepository: ProductSearchRepositoryImpl): ProductSearchRepository
}