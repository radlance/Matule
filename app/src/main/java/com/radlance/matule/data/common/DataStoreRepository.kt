package com.radlance.matule.data.common

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setOnBoardingViewed(viewed: Boolean)
    fun getViewingStatus(): Flow<Boolean>
    suspend fun setLoggedInStatus(loggedIn: Boolean)
    fun getLoggedInStatus(): Flow<Boolean>
    fun getNotificationsCount(): Flow<Int>
    suspend fun updateNotificationCount(notificationsCount: Int)
}