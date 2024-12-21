package com.radlance.matule.presentation.profile

import com.radlance.matule.domain.authorization.User
import com.radlance.matule.domain.user.UserRepository
import com.radlance.matule.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _userData = MutableStateFlow(User())
    val userData: StateFlow<User> = _userData.onStart {
        _userData.value = userRepository.getCurrentUserData()
    }.stateInViewModel(User())
}