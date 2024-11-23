package com.radlance.matule.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.onboarding.NavigationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigationRepository: NavigationRepository
) : ViewModel() {
    private val onBoardingAlreadyViewed = navigationRepository.getOnBoardingViewingStatus()
    private val userAlreadyLoggedIn = navigationRepository.getLoggedInStatus()

    val navigationState: StateFlow<NavigationState> =
        combine(onBoardingAlreadyViewed, userAlreadyLoggedIn) { onBoardingViewed, userLoggedIn ->
            when {
                onBoardingViewed && userLoggedIn -> NavigationState.NavigateToHome
                onBoardingViewed && !userLoggedIn -> NavigationState.NavigateToSignIn
                else -> NavigationState.NavigateToOnBoardingFirst
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            NavigationState.NavigateToOnBoardingFirst
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