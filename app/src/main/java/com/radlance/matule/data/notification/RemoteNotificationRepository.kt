package com.radlance.matule.data.notification

import com.radlance.matule.data.database.remote.RemoteMapper
import com.radlance.matule.data.database.remote.entity.NotificationEntity
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.domain.notification.NotificationDataStore
import com.radlance.matule.domain.notification.NotificationRepository
import com.radlance.matule.domain.remote.FetchResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteNotificationRepository @Inject constructor(
    private val supabaseClient: SupabaseClient,
    private val notificationDataStore: NotificationDataStore
)  : NotificationRepository, RemoteMapper() {
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
                FetchResult.Unauthorized()
            }
        } catch (e: Exception) {
            FetchResult.Error(emptyList())
        }
    }

    override fun getNotificationsCount(): Flow<Int> {
        return notificationDataStore.getNotificationsCount()
    }

    override suspend fun updateNotificationsCount(notificationsCount: Int) {
        notificationDataStore.updateNotificationsCount(notificationsCount)
    }

    override suspend fun setNotificationRead(notificationId: Int): FetchResult<Unit> {
        return try {
            val user = supabaseClient.auth.currentSessionOrNull()?.user
            user?.let {
                supabaseClient.from("notification").update(
                    {
                        NotificationEntity::isRead setTo true
                    }
                ) {
                    filter {
                        NotificationEntity::id eq notificationId
                        NotificationEntity::userId eq user.id
                    }
                }
            }
            FetchResult.Success(Unit)
        } catch (e: Exception) {
            FetchResult.Error(Unit)
        }
    }
}