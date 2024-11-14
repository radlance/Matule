package com.radlance.matule.presentation.signup

import com.radlance.matule.domain.authorization.AuthResult
import javax.inject.Inject

class SignUpResultMapper @Inject constructor() : AuthResult.Mapper<SignUpResultUiState> {
    override fun mapSuccess(): SignUpResultUiState {
        return SignUpResultUiState.Success
    }

    override fun mapError(noConnection: Boolean): SignUpResultUiState {
        val message = if(noConnection) {
            "Нет соединения с интернетом"
        } else {
            "Ошибка сервера"
        }

        return SignUpResultUiState.Error(message)
    }
}