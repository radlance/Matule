package com.radlance.matule.di

import com.radlance.matule.data.auth.AuthRepositoryImpl
import com.radlance.matule.domain.authorization.AuthRepository
import com.radlance.matule.common.ResourceManager
import com.radlance.matule.common.ResourceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {
    @Binds
    fun provideRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    fun provideResourceManager(resourceManager: ResourceManagerImpl): ResourceManager
}