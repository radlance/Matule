package com.radlance.matule.di

import android.content.Context
import com.radlance.matule.BuildConfig
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapModule {
    @Singleton
    @Provides
    fun provideMapKit(context: Context): MapKit {
        MapKitFactory.setApiKey(BuildConfig.YANDEX_MAP_KIT_KEY)
        MapKitFactory.initialize(context)

        return MapKitFactory.getInstance()
    }
}