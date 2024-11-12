package com.radlance.matule.data.onboarding

import com.radlance.matule.data.DataStoreManager
import com.radlance.matule.domain.OnBoardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : OnBoardingRepository {
    override suspend fun setOnBoardingViewed() {
        dataStoreManager.setOnBoardingViewed()
    }

    override fun getViewingStatus(): Flow<Boolean> {
        return dataStoreManager.getViewingStatus()
    }
}