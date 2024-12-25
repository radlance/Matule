package com.radlance.matule.di

import android.content.Context
import com.radlance.matule.BuildConfig
import com.radlance.matule.data.database.local.MatuleDao
import com.radlance.matule.data.database.local.MatuleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.storage.Storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MatuleDatabase {
        return MatuleDatabase.getInstance(context)
    }
    
    @Singleton
    @Provides
    fun provideMatuleDao(database: MatuleDatabase): MatuleDao {
        return database.getMatuleDao()
    }

    @Singleton
    @Provides
    fun provideSupabaseClient(): SupabaseClient {
        val supabase = createSupabaseClient(
            supabaseUrl = "https://osoknxtwcppulkimwpjo.supabase.co",
            supabaseKey = BuildConfig.SUPABASE_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Storage)
            defaultSerializer = KotlinXSerializer()
        }

        return supabase
    }
}