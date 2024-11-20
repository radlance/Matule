package com.radlance.matule.presentation.authorization.signin

data class VerificationUiState(
    val showRecoveryDialog: Boolean = false,
    val hideKeyBoard: Boolean = false,
    val initialPhrase: String = String(),
    val currentPassword: String = String()
)
