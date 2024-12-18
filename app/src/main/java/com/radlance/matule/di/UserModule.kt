package com.radlance.matule.di

import com.radlance.matule.data.user.UserRepositoryImpl
import com.radlance.matule.domain.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserModule {
    @Binds
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}