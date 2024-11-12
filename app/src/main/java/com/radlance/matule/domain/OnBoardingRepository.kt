package com.radlance.matule.domain

import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {
    suspend fun setOnBoardingViewed()
    fun getViewingStatus(): Flow<Boolean>
}