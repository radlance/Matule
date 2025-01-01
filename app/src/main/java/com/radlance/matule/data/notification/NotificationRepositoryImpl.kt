package com.radlance.matule.data.notification

import com.radlance.matule.data.common.BaseRepository
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.domain.notification.NotificationRepository
import com.radlance.matule.domain.remote.FetchResult
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    supabaseClient: SupabaseClient,
    localNotificationRepository: NotificationRepository,
    remoteNotificationRepository: NotificationRepository
) : NotificationRepository, BaseRepository<NotificationRepository>(
    supabaseClient,
    localNotificationRepository,
    remoteNotificationRepository
) {
    override suspend fun loadNotifications(): FetchResult<List<Notification>> {
        return getRepository().loadNotifications()
    }

    override fun getNotificationsCount(): Flow<Int> {
        return getRepository().getNotificationsCount()
    }

    override suspend fun updateNotificationsCount(notificationsCount: Int) {
        return getRepository().updateNotificationsCount(notificationsCount)
    }

    override suspend fun setNotificationRead(notificationId: Int): FetchResult<Unit> {
        return getRepository().setNotificationRead(notificationId)
    }
}