package com.radlance.matule.domain.onboarding

import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {
    suspend fun setOnBoardingViewed()
    fun getViewingStatus(): Flow<Boolean>
}