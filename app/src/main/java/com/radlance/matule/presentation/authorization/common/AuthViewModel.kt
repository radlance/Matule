package com.radlance.matule.presentation.authorization.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.authorization.AuthRepository
import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.domain.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
data class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val mapper: AuthResult.Mapper<AuthResultUiState>
) : ViewModel() {
    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState>
        get() = _authUiState.asStateFlow()

    private val _authResultUiState = MutableStateFlow<AuthResultUiState>(AuthResultUiState.Initial)

    val authResultUiState: StateFlow<AuthResultUiState>
        get() = _authResultUiState.asStateFlow()

    private val _googleSignInResult = MutableStateFlow<AuthResultUiState>(AuthResultUiState.Initial)
    val googleSignInResult: StateFlow<AuthResultUiState>
        get() = _googleSignInResult.asStateFlow()


    fun signUp(name: String, email: String, password: String) {
        performAuthAction(email, password, name, isSignUp = true)
    }

    fun signIn(email: String, password: String) {
        performAuthAction(email, password, isSignUp = false)
    }

    fun signInWithGoogle(googleIdToken: String, rawNonce: String) {
        viewModelScope.launch {
            _googleSignInResult.value = AuthResultUiState.Loading()
            val result = authRepository.signInWithGoogle(googleIdToken, rawNonce)
            _googleSignInResult.value = result.map(mapper)
        }
    }

    fun sendOtp(email: String) {
        performAuthAction(email = email, sendOtp = true)
    }

    private fun performAuthAction(
        email: String? = null,
        password: String? = null,
        name: String? = null,
        isSignUp: Boolean = false,
        sendOtp: Boolean = false
    ) {
        validateFields(email, password, name)

        with(authUiState.value) {
            if (isValidEmail && (isValidPassword && (isSignUp && isValidName || !isSignUp) || sendOtp)) {
                viewModelScope.launch {
                    _authResultUiState.value = AuthResultUiState.Loading()

                    val result = when {
                        isSignUp -> authRepository.signUp(User(email = email!!, password = password!!, name = name!!))
                        sendOtp -> authRepository.sendOtp(email!!)
                        else -> authRepository.signIn(User(email = email!!, password = password!!))
                    }

                    _authResultUiState.value = result.map(mapper)
                }
            }
        }
    }


    fun updateActionButtonState(isEnabled: Boolean) {
        _authUiState.update { currentState ->
            currentState.copy(isEnabledButton = isEnabled)
        }
    }

    private fun validateFields(
        email: String? = null,
        password: String? = null,
        name: String? = null
    ) {
        _authUiState.update { currentState ->
            currentState.copy(
                isValidName = name?.isNotBlank() ?: false,
                isValidEmail = email?.let { Regex("^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$").matches(it) }
                    ?: false,
                isValidPassword = password?.let { password.isNotBlank() && password.length >= 6 }
                    ?: false

            )
        }
    }

    fun resetNameError() {
        _authUiState.update { currentState ->
            currentState.copy(isValidName = true)
        }
    }

    fun resetEmailError() {
        _authUiState.update { currentState ->
            currentState.copy(isValidEmail = true)
        }
    }

    fun resetPasswordError() {
        _authUiState.update { currentState ->
            currentState.copy(isValidPassword = true)
        }
    }
}