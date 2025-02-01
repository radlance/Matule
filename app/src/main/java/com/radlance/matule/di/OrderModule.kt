package com.radlance.matule.di

import com.radlance.matule.domain.location.LocationClientResult
import com.radlance.matule.presentation.common.LocationClientResultUiState
import com.radlance.matule.presentation.order.LocationClientResultMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface OrderModule {
    @Binds
    fun provideLocationClientResultMapper(
        locationClientResultMapper: LocationClientResultMapper
    ): LocationClientResult.Mapper<LocationClientResultUiState>
}