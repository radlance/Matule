package com.radlance.matule.di

import com.radlance.matule.data.history.HistoryRepositoryImpl
import com.radlance.matule.domain.history.HistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HistoryModule {
    @Binds
    fun provideRepository(historyRepository: HistoryRepositoryImpl): HistoryRepository
}