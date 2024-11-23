package com.radlance.matule.domain.onboarding

import kotlinx.coroutines.flow.Flow

interface NavigationRepository {
    suspend fun setOnBoardingViewed()
    fun getOnBoardingViewingStatus(): Flow<Boolean>

    suspend fun setUserLoggedIn()
    fun getLoggedInStatus(): Flow<Boolean>
}