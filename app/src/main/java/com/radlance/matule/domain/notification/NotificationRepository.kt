package com.radlance.matule.domain.notification

import com.radlance.matule.data.common.Repository
import com.radlance.matule.domain.remote.FetchResult
import kotlinx.coroutines.flow.Flow

interface NotificationRepository : Repository {
    suspend fun loadNotifications(): FetchResult<List<Notification>>
    fun getNotificationsCount(): Flow<Int>
    suspend fun updateNotificationsCount(notificationsCount: Int)
    suspend fun setNotificationRead(notificationId: Int): FetchResult<Unit>
}