package com.radlance.matule.presentation.authorization.signup

data class SignUpUiState(
    val isValidName: Boolean = true,
    val isValidEmail: Boolean = true,
    val isValidPassword: Boolean = true,
    val isEnabledButton: Boolean = true
)
