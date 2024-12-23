package com.radlance.matule.navigation.drawer

import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.authorization.AuthRepository
import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.domain.authorization.User
import com.radlance.matule.domain.notification.NotificationRepository
import com.radlance.matule.domain.onboarding.NavigationRepository
import com.radlance.matule.domain.remote.FetchResult
import com.radlance.matule.domain.user.UserRepository
import com.radlance.matule.presentation.authorization.common.AuthResultUiState
import com.radlance.matule.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawerStateViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val navigationRepository: NavigationRepository,
    private val notificationRepository: NotificationRepository,
    private val mapper: AuthResult.Mapper<AuthResultUiState>
) : BaseViewModel() {
    private val _drawerState = MutableStateFlow<DrawerState>(DrawerState.Collapsed)
    val drawerState: StateFlow<DrawerState>
        get() = _drawerState.asStateFlow()

    private val _signOutState = MutableStateFlow<AuthResultUiState>(AuthResultUiState.Initial)
    val signOutState: StateFlow<AuthResultUiState>
        get() = _signOutState.asStateFlow()

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> get() = _user.stateInViewModel(User())

    val hasNotifications = notificationRepository.getNotificationsCount().onStart {
        fetchNotificationCount()
    }.stateInViewModel(0)

    private fun fetchNotificationCount() {
        viewModelScope.launch {
            val fetchResult = notificationRepository.loadNotifications()
            if (fetchResult is FetchResult.Success) {
                val unreadNotifications = fetchResult.data.filter { !it.isRead }
                notificationRepository.updateNotificationsCount(unreadNotifications.size)
            }
        }
    }

    fun getCurrentUserData() {
        viewModelScope.launch {

            val currentUserData = userRepository.getCurrentUserData()
            if (_user.value != currentUserData) {
                _user.value = currentUserData
            }
        }
    }

    fun changeDrawerState() {
        _drawerState.value = if (drawerState.value is DrawerState.Expanded) {
            DrawerState.Collapsed
        } else {
            DrawerState.Expanded
        }
    }

    fun setExpandedState() {
        _drawerState.value = DrawerState.Expanded
    }

    fun setCollapsedState() {
        _drawerState.value = DrawerState.Collapsed
    }

    fun signOut() {
        viewModelScope.launch {
            _signOutState.value = AuthResultUiState.Loading("Выход...")
            val result = authRepository.signOut().map(mapper)
            _signOutState.value = result
        }
    }

    fun leaveHomeScreen() {
        viewModelScope.launch {
            navigationRepository.setUserLoggedIn(false)
        }
    }
}