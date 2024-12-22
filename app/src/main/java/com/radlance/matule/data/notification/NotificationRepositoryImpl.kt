package com.radlance.matule.data.notification

import android.util.Log
import com.radlance.matule.data.common.Mapper
import com.radlance.matule.data.database.remote.entity.NotificationEntity
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.domain.notification.NotificationRepository
import com.radlance.matule.domain.remote.FetchResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
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
            Log.e("NotificationRepositoryImpl", e.message!!)
            FetchResult.Error(emptyList())
        }
    }
}