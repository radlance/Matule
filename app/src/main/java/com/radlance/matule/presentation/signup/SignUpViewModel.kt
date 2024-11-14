package com.radlance.matule.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.authorization.AuthRepository
import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.domain.authorization.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val mapper: AuthResult.Mapper<SignUpResultUiState>
) : ViewModel() {
    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState: StateFlow<SignUpUiState>
        get() = _signUpUiState.asStateFlow()

    private val _signUpResultUiState: MutableStateFlow<SignUpResultUiState> =
        MutableStateFlow(SignUpResultUiState.Initial)

    val signUpResultUiState: StateFlow<SignUpResultUiState>
        get() = _signUpResultUiState.asStateFlow()

    fun registerUser(name: String, email: String, password: String) {
        validateFields(name, email, password)

        with(signUpUiState.value) {
            if (isValidName && isValidEmail && isValidPassword) {
                viewModelScope.launch {
                    _signUpResultUiState.value = SignUpResultUiState.Loading
                    updateSignUpButtonState(false)

                    val result = authRepository.signUp(User(name, email, password))
                    _signUpResultUiState.value = result.map(mapper)

                    updateSignUpButtonState(true)
                }
            }
        }
    }

    private fun updateSignUpButtonState(isEnabled: Boolean) {
        _signUpUiState.update { currentState ->
            currentState.copy(isEnabledButton = isEnabled)
        }
    }

    private fun validateFields(name: String, email: String, password: String) {
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