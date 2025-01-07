package com.radlance.matule.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.radlance.matule.data.common.DataStoreRepository
import com.radlance.matule.data.common.DataStoreRepositoryImpl
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
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStoreRepository {
        return DataStoreRepositoryImpl(applicationContext.dataStore)
    }

    @Provides
    @Singleton
    fun provideAuth(supabaseClient: SupabaseClient): Auth {
        return supabaseClient.auth
    }

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }
}