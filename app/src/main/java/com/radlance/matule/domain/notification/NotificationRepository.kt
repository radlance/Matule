package com.radlance.matule.domain.notification

import com.radlance.matule.domain.remote.FetchResult

interface NotificationRepository {
    suspend fun loadNotifications(): FetchResult<List<Notification>>
}