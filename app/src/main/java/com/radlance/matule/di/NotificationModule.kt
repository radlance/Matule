package com.radlance.matule.di

import com.radlance.matule.data.common.DataStoreRepository
import com.radlance.matule.data.database.local.MatuleDao
import com.radlance.matule.data.notification.LocalNotificationRepository
import com.radlance.matule.data.notification.NotificationDataStoreImpl
import com.radlance.matule.data.notification.NotificationRepositoryImpl
import com.radlance.matule.data.notification.RemoteNotificationRepository
import com.radlance.matule.domain.notification.NotificationDataStore
import com.radlance.matule.domain.notification.NotificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {
    @Provides
    @Singleton
    fun provideNotificationDataStore(
        dataStoreRepository: DataStoreRepository
    ): NotificationDataStore {
        return NotificationDataStoreImpl(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideLocalNotificationRepository(
        dao: MatuleDao,
        notificationDataStore: NotificationDataStore
    ): LocalNotificationRepository {
        return LocalNotificationRepository(dao, notificationDataStore)
    }

    @Provides
    @Singleton
    fun provideRemoteProductRepository(
        supabaseClient: SupabaseClient,
        notificationDataStore: NotificationDataStore
    ): RemoteNotificationRepository {
        return RemoteNotificationRepository(supabaseClient, notificationDataStore)
    }

    @Provides
    @Singleton
    fun provideNotificationRepository(
        supabaseClient: SupabaseClient,
        localNotificationRepository: LocalNotificationRepository,
        remoteNotificationRepository: RemoteNotificationRepository
    ): NotificationRepository {
        return NotificationRepositoryImpl(
            supabaseClient,
            localNotificationRepository,
            remoteNotificationRepository
        )
    }
}