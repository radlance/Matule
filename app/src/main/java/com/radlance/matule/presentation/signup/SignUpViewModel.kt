package com.radlance.matule.presentation.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState: StateFlow<SignUpUiState>
        get() = _signUpUiState.asStateFlow()

    fun registerUser(name: String, email: String, password: String) {
        validateFields(name, email, password)
        with(signUpUiState.value) {
            if (isValidName && isValidEmail && isValidPassword) {

            }
        }
    }

    private fun validateFields(name: String, email: String, password: String) {
        _signUpUiState.update { currentState ->
            currentState.copy(
                isValidName = name.isNotBlank(),
                isValidEmail = Regex("^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$").matches(email),
                isValidPassword = password.isNotBlank()
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