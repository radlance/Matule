package com.radlance.matule.presentation.authorization.signin

import com.radlance.matule.domain.user.User

interface AccountManager {
    suspend fun signInWithGoogle(): AccountManagerUiState
    suspend fun signUp(email: String, password: String): AccountManagerUiState
    suspend fun signIn(): AccountManagerUiState
}

interface AccountManagerUiState {
    fun show(
        onSuccess: (token: String, rawNonce: String, user: User?) -> Unit,
        onError: (String) -> Unit
    )

    data class Success(
        private val token: String? = null,
        private val rawNonce: String? = null,
        private val user: User? = null
    ) : AccountManagerUiState {
        override fun show(
            onSuccess: (token: String, rawNonce: String, user: User?) -> Unit,
            onError: (String) -> Unit
        ) {
            onSuccess(token ?: "", rawNonce ?: "", user)
        }
    }

    data class Error(private val message: String = "") : AccountManagerUiState {
        override fun show(
            onSuccess: (token: String, rawNonce: String, user: User?) -> Unit,
            onError: (String) -> Unit
        ) {
            onError(message)
        }
    }
}
