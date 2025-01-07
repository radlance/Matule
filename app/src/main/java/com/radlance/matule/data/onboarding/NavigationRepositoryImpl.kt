package com.radlance.matule.data.onboarding

import com.radlance.matule.data.common.DataStoreRepository
import com.radlance.matule.domain.onboarding.NavigationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NavigationRepositoryImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : NavigationRepository {
    override suspend fun setOnBoardingViewed() {
        dataStoreRepository.setOnBoardingViewed()
    }

    override fun getOnBoardingViewingStatus(): Flow<Boolean> {
        return dataStoreRepository.getViewingStatus()
    }

    override suspend fun setUserLoggedIn(loggedIn: Boolean) {
        return dataStoreRepository.setLoggedInStatus(loggedIn)
    }

    override fun getLoggedInStatus(): Flow<Boolean> {
        return dataStoreRepository.getLoggedInStatus()
    }
}