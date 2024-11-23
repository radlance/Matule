package com.radlance.matule.di

import com.radlance.matule.data.onboarding.NavigationRepositoryImpl
import com.radlance.matule.domain.onboarding.NavigationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface OnBoardingModule {
    @Binds
    fun provideRepository(onBoardingRepositoryImpl: NavigationRepositoryImpl): NavigationRepository
}