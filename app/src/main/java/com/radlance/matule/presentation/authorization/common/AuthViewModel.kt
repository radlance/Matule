package com.radlance.matule.presentation.authorization.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.authorization.AuthRepository
import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.domain.authorization.User
import com.radlance.matule.presentation.authorization.signup.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val mapper: AuthResult.Mapper<AuthResultUiState>
) : ViewModel() {
    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState: StateFlow<SignUpUiState>
        get() = _signUpUiState.asStateFlow()

    private val _authResultUiState: MutableStateFlow<AuthResultUiState> =
        MutableStateFlow(AuthResultUiState.Initial)

    val authResultUiState: StateFlow<AuthResultUiState>
        get() = _authResultUiState.asStateFlow()

    fun signUp(name: String, email: String, password: String) {
        performAuthAction(
            email = email,
            password = password,
            name = name,
            isSignUp = true
        )
    }

    fun signIn(email: String, password: String) {
        performAuthAction(
            email = email,
            password = password,
            isSignUp = false
        )
    }

    private fun performAuthAction(
        email: String, password: String,
        name: String = "",
        isSignUp: Boolean
    ) {
        validateFields(email = email, password = password, name = name)

        with(signUpUiState.value) {
            if (isValidEmail && isValidPassword && (isSignUp && isValidName || !isSignUp)) {
                viewModelScope.launch {
                    _authResultUiState.value = AuthResultUiState.Loading
                    updateActionButtonState(false)

                    val result = if (isSignUp) {
                        authRepository.signUp(User(email = email, password = password, name = name))
                    } else {
                        authRepository.signIn(User(email = email, password = password))
                    }

                    _authResultUiState.value = result.map(mapper)
                    updateActionButtonState(true)
                }
            }
        }
    }



    private fun updateActionButtonState(isEnabled: Boolean) {
        _signUpUiState.update { currentState ->
            currentState.copy(isEnabledButton = isEnabled)
        }
    }

    private fun validateFields(email: String, password: String, name: String = "") {
        _signUpUiState.update { currentState ->
            currentState.copy(
                isValidName = name.isNotBlank(),
                isValidEmail = Regex("^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$").matches(email),
                isValidPassword = password.isNotBlank() && password.length >= 6
            )
        }
    }

    fun resetNameError() {
        _signUpUiState.update { currentState ->
            currentState.copy(isValidName = true)
        }
    }

    fun resetEmailError() {
        _signUpUiState.update { currentState ->
            currentState.copy(isValidEmail = true)
        }
    }

    fun resetPasswordError() {
        _signUpUiState.update { currentState ->
            currentState.copy(isValidPassword = true)
        }
    }
}