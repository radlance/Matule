package com.radlance.matule.presentation.authorization.signin

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.authorization.AuthRepository
import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.presentation.authorization.common.AuthResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val mapper: AuthResult.Mapper<AuthResultUiState>
) : ViewModel() {
    private val otpItems = mutableStateListOf("", "", "", "", "", "")
    private val _resendingUiState = MutableStateFlow<AuthResultUiState>(AuthResultUiState.Initial)
    val resendingUiState: StateFlow<AuthResultUiState>
        get() = _resendingUiState

    fun getOtpItem(index: Int): String {
        return otpItems[index]
    }

    fun updateOtpItem(index: Int, value: String) {
        otpItems[index] = value
    }

    fun resendOtp(email: String) {
        viewModelScope.launch {
            _resendingUiState.value = AuthResultUiState.Loading("Загрузка…")
            val result = authRepository.sendOtp(email)
            _resendingUiState.value = result.map(mapper)
        }
    }
}