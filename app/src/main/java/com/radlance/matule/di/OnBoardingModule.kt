package com.radlance.matule.di

import com.radlance.matule.data.onboarding.OnBoardingRepositoryImpl
import com.radlance.matule.domain.onboarding.OnBoardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface OnBoardingModule {
    @Binds
    fun provideRepository(onBoardingRepositoryImpl: OnBoardingRepositoryImpl): OnBoardingRepository
}