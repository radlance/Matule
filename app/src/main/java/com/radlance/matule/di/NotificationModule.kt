package com.radlance.matule.di

import com.radlance.matule.data.common.DataStoreManager
import com.radlance.matule.data.database.local.MatuleDao
import com.radlance.matule.data.notification.LocalNotificationRepository
import com.radlance.matule.data.notification.NotificationRepositoryImpl
import com.radlance.matule.data.notification.RemoteNotificationRepository
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
    fun provideLocalNotificationRepository(
        dao: MatuleDao,
        dataStoreManager: DataStoreManager
    ): LocalNotificationRepository {
        return LocalNotificationRepository(dao, dataStoreManager)
    }

    @Provides
    @Singleton
    fun provideRemoteProductRepository(
        supabaseClient: SupabaseClient,
        dataStoreManager: DataStoreManager
    ): RemoteNotificationRepository {
        return RemoteNotificationRepository(supabaseClient, dataStoreManager)
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