package com.radlance.matule.di

import com.radlance.matule.data.notification.NotificationRepositoryImpl
import com.radlance.matule.domain.notification.NotificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NotificationModule {
    @Binds
    fun provideRepository(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationRepository
}