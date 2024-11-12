package com.radlance.matule.di

import android.content.Context
import com.radlance.matule.data.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStoreManager {
        return DataStoreManager(applicationContext)
    }
}