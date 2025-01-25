package com.radlance.matule

import com.radlance.matule.presentation.authorization.signin.AccountManager
import com.radlance.matule.presentation.authorization.signin.AccountManagerUiState

class FakeAccountManager : AccountManager {
    override suspend fun signInWithGoogle(): AccountManagerUiState {
        return AccountManagerUiState.Error()
    }

    override suspend fun signUp(email: String, password: String): AccountManagerUiState {
        return AccountManagerUiState.Error()
    }

    override suspend fun signIn(): AccountManagerUiState {
        return AccountManagerUiState.Error()
    }
}