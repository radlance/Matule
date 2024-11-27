package com.radlance.matule.di

import com.radlance.matule.data.home.HomeRepositoryImpl
import com.radlance.matule.domain.home.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HomeModule {
    @Binds
    fun provideRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}