package com.radlance.matule.data.onboarding

import com.radlance.matule.data.common.DataStoreManager
import com.radlance.matule.domain.onboarding.NavigationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NavigationRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : NavigationRepository {
    override suspend fun setOnBoardingViewed() {
        dataStoreManager.setOnBoardingViewed()
    }

    override fun getOnBoardingViewingStatus(): Flow<Boolean> {
        return dataStoreManager.getViewingStatus()
    }

    override suspend fun setUserLoggedIn(loggedIn: Boolean) {
        return dataStoreManager.setLoggedInStatus(loggedIn)
    }

    override fun getLoggedInStatus(): Flow<Boolean> {
        return dataStoreManager.getLoggedInStatus()
    }
}