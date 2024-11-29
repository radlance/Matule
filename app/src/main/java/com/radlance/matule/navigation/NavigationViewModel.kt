package com.radlance.matule.navigation

import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.onboarding.NavigationRepository
import com.radlance.matule.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigationRepository: NavigationRepository
) : BaseViewModel() {
    private val onBoardingAlreadyViewed = navigationRepository.getOnBoardingViewingStatus()
    private val userAlreadyLoggedIn = navigationRepository.getLoggedInStatus()

    val navigationState: StateFlow<OnBoardingNavigationState> =
        combine(onBoardingAlreadyViewed, userAlreadyLoggedIn) { onBoardingViewed, userLoggedIn ->
            when {
                onBoardingViewed && userLoggedIn -> OnBoardingNavigationState.NavigateToHome
                onBoardingViewed && !userLoggedIn -> OnBoardingNavigationState.NavigateToSignIn
                else -> OnBoardingNavigationState.NavigateToOnBoardingFirst
            }
        }.stateInViewModel(
            initialValue = OnBoardingNavigationState.NavigateToOnBoardingFirst
        )

    fun setOnBoardingViewed() {
        viewModelScope.launch {
            navigationRepository.setOnBoardingViewed()
        }
    }

    fun setUserLoggedIn() {
        viewModelScope.launch {
            navigationRepository.setUserLoggedIn()
        }
    }
}