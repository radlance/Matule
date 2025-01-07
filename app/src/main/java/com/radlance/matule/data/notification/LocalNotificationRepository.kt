package com.radlance.matule.data.notification

import com.radlance.matule.data.common.DataStoreRepository
import com.radlance.matule.data.database.local.LocalMapper
import com.radlance.matule.data.database.local.MatuleDao
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.domain.notification.NotificationRepository
import com.radlance.matule.domain.remote.FetchResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalNotificationRepository @Inject constructor(
    private val dao: MatuleDao,
    private val dataStoreRepository: DataStoreRepository
) : NotificationRepository, LocalMapper() {
    override suspend fun loadNotifications(): FetchResult<List<Notification>> {
        val notifications = dao.getNotifications()
        return FetchResult.Success(
            notifications.map { notificationEntity ->
                notificationEntity.toNotification()
            }
        )
    }

    override fun getNotificationsCount(): Flow<Int> {
        return dataStoreRepository.getNotificationsExistStatus()
    }

    override suspend fun updateNotificationsCount(notificationsCount: Int) {
        dataStoreRepository.setNotificationExistStatus(notificationsCount)
    }

    override suspend fun setNotificationRead(notificationId: Int): FetchResult<Unit> {
        dao.setNotificationRead(notificationId)
        return FetchResult.Success(Unit)
    }
}