package com.radlance.matule.di

import com.radlance.matule.data.common.DataStoreRepository
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
        dataStoreRepository: DataStoreRepository
    ): LocalNotificationRepository {
        return LocalNotificationRepository(dao, dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideRemoteProductRepository(
        supabaseClient: SupabaseClient,
        dataStoreRepository: DataStoreRepository
    ): RemoteNotificationRepository {
        return RemoteNotificationRepository(supabaseClient, dataStoreRepository)
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