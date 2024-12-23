package com.radlance.matule.data.notification

import com.radlance.matule.data.common.DataStoreManager
import com.radlance.matule.data.common.Mapper
import com.radlance.matule.data.database.remote.entity.NotificationEntity
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.domain.notification.NotificationRepository
import com.radlance.matule.domain.remote.FetchResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient,
    private val dataStoreManager: DataStoreManager
) : NotificationRepository, Mapper() {
    override suspend fun loadNotifications(): FetchResult<List<Notification>> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            if (user != null) {
                val notificationEntities =
                    supabaseClient.from("notification").select {
                        filter {
                            NotificationEntity::userId eq user.id
                        }
                    }.decodeList<NotificationEntity>()

                FetchResult.Success(notificationEntities.map { it.toNotification() })
            } else {
                FetchResult.Error(emptyList())
            }
        } catch (e: Exception) {
            FetchResult.Error(emptyList())
        }
    }

    override fun getNotificationsCount(): Flow<Int> {
        return dataStoreManager.getNotificationsExistStatus()
    }

    override suspend fun updateNotificationsCount(notificationsCount: Int) {
        dataStoreManager.setNotificationExistStatus(notificationsCount)
    }
}