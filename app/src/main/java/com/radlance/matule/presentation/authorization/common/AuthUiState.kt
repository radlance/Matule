package com.radlance.matule.presentation.authorization.common

data class AuthUiState(
    val isValidName: Boolean = true,
    val isValidEmail: Boolean = true,
    val isValidPassword: Boolean = true,
    val isEnabledButton: Boolean = true
)
