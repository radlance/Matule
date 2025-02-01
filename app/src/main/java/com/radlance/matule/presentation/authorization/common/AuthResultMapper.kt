package com.radlance.matule.presentation.authorization.common

import com.radlance.matule.R
import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.common.ResourceManager
import javax.inject.Inject

class AuthResultMapper @Inject constructor(
    private val resourceManager: ResourceManager
) : AuthResult.Mapper<AuthResultUiState> {
    override fun mapSuccess(): AuthResultUiState {
        return AuthResultUiState.Success
    }

    override fun mapError(noConnection: Boolean, statusCode: Int): AuthResultUiState {
        val messageResId = if (noConnection) {
            R.string.no_connection
        } else when (statusCode) {
            400 -> R.string.incorrect_data_error
            422 -> R.string.account_already_exist
            403 -> R.string.verification_error
            else -> R.string.unknown_error
        }

        return AuthResultUiState.Error(resourceManager.getString(messageResId))
    }
}