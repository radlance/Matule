package com.radlance.matule.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.radlance.matule.common.ResourceManager
import com.radlance.matule.data.location.BaseLocationClient
import com.radlance.matule.domain.location.LocationClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocationModule {
    @Provides
    @Singleton
    fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideLocationClient(
        @ApplicationContext context: Context,
        fusedLocationProviderClient: FusedLocationProviderClient,
        resourceManager: ResourceManager
    ): LocationClient {
        return BaseLocationClient(
            context = context,
            client = fusedLocationProviderClient,
            resourceManager = resourceManager
        )
    }
}
