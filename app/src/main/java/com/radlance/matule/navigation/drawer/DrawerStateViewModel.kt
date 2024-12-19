package com.radlance.matule.navigation.drawer

import com.radlance.matule.domain.authorization.User
import com.radlance.matule.domain.user.UserRepository
import com.radlance.matule.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawerStateViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {
    private val _drawerState = MutableStateFlow<DrawerState>(DrawerState.Collapsed)
    val drawerState: StateFlow<DrawerState>
        get() = _drawerState

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> get() = _user.stateInViewModel(User())

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
}