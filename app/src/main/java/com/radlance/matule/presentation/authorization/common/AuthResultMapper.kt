package com.radlance.matule.presentation.authorization.common

import com.radlance.matule.domain.authorization.AuthResult
import javax.inject.Inject

class AuthResultMapper @Inject constructor() : AuthResult.Mapper<AuthResultUiState> {
    override fun mapSuccess(): AuthResultUiState {
        return AuthResultUiState.Success
    }

    override fun mapError(noConnection: Boolean): AuthResultUiState {
        val message = if(noConnection) {
            "Нет соединения с интернетом"
        } else {
            "Ошибка сервера"
        }

        return AuthResultUiState.Error(message)
    }
}