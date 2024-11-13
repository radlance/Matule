package com.radlance.matule.di

import com.radlance.matule.data.signup.AuthRepositoryImpl
import com.radlance.matule.domain.authorization.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {
    @Binds
    fun provideRepository(repository: AuthRepositoryImpl): AuthRepository
}