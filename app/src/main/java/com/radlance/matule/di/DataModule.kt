package com.radlance.matule.di

import android.content.Context
import com.radlance.matule.BuildConfig
import com.radlance.matule.data.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.serializer.KotlinXSerializer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStoreManager {
        return DataStoreManager(applicationContext)
    }

    @Singleton
    @Provides
    fun provideSupabaseClient(): SupabaseClient {
        val supabase = createSupabaseClient(
            supabaseUrl = "https://osoknxtwcppulkimwpjo.supabase.co",
            supabaseKey = BuildConfig.SUPABASE_KEY
        ) {
            install(Auth)
            defaultSerializer = KotlinXSerializer()
        }

        return supabase
    }
}