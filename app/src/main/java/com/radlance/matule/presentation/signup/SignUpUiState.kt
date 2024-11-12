package com.radlance.matule.presentation.signup

data class SignUpUiState(
    val isValidName: Boolean = true,
    val isValidEmail: Boolean = true,
    val isValidPassword: Boolean = true
)
