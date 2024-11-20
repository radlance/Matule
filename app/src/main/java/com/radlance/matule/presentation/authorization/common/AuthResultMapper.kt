package com.radlance.matule.presentation.authorization.common

import com.radlance.matule.domain.authorization.AuthResult
import javax.inject.Inject

class AuthResultMapper @Inject constructor() : AuthResult.Mapper<AuthResultUiState> {
    override fun mapSuccess(): AuthResultUiState {
        return AuthResultUiState.Success
    }

    override fun mapError(noConnection: Boolean, statusCode: Int): AuthResultUiState {
        val message = if(noConnection) {
            "Отсутствует соединение с интернетом"
        } else if(statusCode == 400) {
            "Данные введены неверно"
        } else if(statusCode == 422) {
            "Аккаунт с такой почтой уже существует"
        } else {
            "Неизвестная ошибка"
        }

        return AuthResultUiState.Error(message)
    }
}