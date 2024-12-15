package com.radlance.matule.di

import com.radlance.matule.data.product.ProductRepositoryImpl
import com.radlance.matule.domain.home.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProductModule {
    @Binds
    fun provideRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}