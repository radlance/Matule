package com.radlance.matule.presentation.order

import com.radlance.matule.domain.user.UserRepository
import com.radlance.matule.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {
    private val _userEmail = MutableStateFlow(String())
    val userEmail: StateFlow<String> = _userEmail.onStart {
        _userEmail.value = userRepository.getCurrentUserData().email
    }.stateInViewModel(String())
}