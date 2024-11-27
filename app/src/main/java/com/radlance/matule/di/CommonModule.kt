package com.radlance.matule.di

import com.radlance.matule.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Singleton
    @Provides
    fun provideSupabaseClient(): SupabaseClient {
        val supabase = createSupabaseClient(
            supabaseUrl = "https://osoknxtwcppulkimwpjo.supabase.co",
            supabaseKey = BuildConfig.SUPABASE_KEY
        ) {
            install(Auth)
            install(Postgrest)
            defaultSerializer = KotlinXSerializer()
        }

        return supabase
    }
}