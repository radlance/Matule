package com.radlance.matule.data.notification

import com.radlance.matule.data.common.DataStoreRepository
import com.radlance.matule.domain.notification.NotificationDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationDataStoreImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : NotificationDataStore {
    override fun getNotificationsCount(): Flow<Int> {
        return dataStoreRepository.getNotificationsCount()
    }

    override suspend fun updateNotificationsCount(notificationsCount: Int) {
        dataStoreRepository.updateNotificationCount(notificationsCount)
    }
}