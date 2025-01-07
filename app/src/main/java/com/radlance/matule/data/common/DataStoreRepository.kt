package com.radlance.matule.data.common

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setOnBoardingViewed()
    fun getViewingStatus(): Flow<Boolean>
    suspend fun setLoggedInStatus(loggedIn: Boolean)
    fun getLoggedInStatus(): Flow<Boolean>
    fun getNotificationsExistStatus(): Flow<Int>
    suspend fun setNotificationExistStatus(notificationsCount: Int)
}