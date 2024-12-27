package com.radlance.matule.presentation.profile.edit

data class ProfileUiState(
    val isValidName: Boolean = true,
    val isValidEmail: Boolean = true,
    val isEnabledButton: Boolean = true
)
