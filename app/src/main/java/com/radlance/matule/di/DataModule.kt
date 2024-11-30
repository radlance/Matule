package com.radlance.matule.di

import android.content.Context
import com.radlance.matule.data.common.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStoreManager {
        return DataStoreManager(applicationContext)
    }

    @Provides
    @Singleton
    fun provideAuth(supabaseClient: SupabaseClient): Auth {
        return supabaseClient.auth
    }
}