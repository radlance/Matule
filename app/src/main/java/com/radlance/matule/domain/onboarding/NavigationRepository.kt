package com.radlance.matule.domain.onboarding

import kotlinx.coroutines.flow.Flow

interface NavigationRepository {
    suspend fun setOnBoardingViewed()
    fun getOnBoardingViewingStatus(): Flow<Boolean>

    suspend fun setUserLoggedIn(loggedIn: Boolean)
    fun getLoggedInStatus(): Flow<Boolean>
}