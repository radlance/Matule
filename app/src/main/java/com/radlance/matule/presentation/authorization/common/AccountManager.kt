package com.radlance.matule.presentation.authorization.common

import android.app.Activity
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.radlance.matule.BuildConfig
import com.radlance.matule.R
import com.radlance.matule.domain.user.User
import java.security.MessageDigest
import java.util.UUID


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

    data class Error(private val message: String) : AccountManagerUiState {
        override fun show(
            onSuccess: (token: String, rawNonce: String, user: User?) -> Unit,
            onError: (String) -> Unit
        ) {
            onError(message)
        }
    }
}


class AccountManager(
    private val activity: Activity
) {
    private val credentialManager = CredentialManager.create(activity)
    private val rawNonce = UUID.randomUUID().toString()

    private val googleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(BuildConfig.SERVER_CLIENT_ID)
        .setNonce(getFormattedNonce(rawNonce))
        .build()

    private val request: GetCredentialRequest = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    suspend fun signInWithGoogle(): AccountManagerUiState {
        return try {
            val result = credentialManager.getCredential(request = request, context = activity)
            val credential = result.credential
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val googleIdToken = googleIdTokenCredential.idToken

            AccountManagerUiState.Success(token = googleIdToken, rawNonce = rawNonce)
        } catch (e: Exception) {
            AccountManagerUiState.Error(message = activity.getString(R.string.sign_in_error))
        }
    }

    suspend fun signUp(email: String, password: String): AccountManagerUiState {
        return try {
            credentialManager.createCredential(
                context = activity,
                request = CreatePasswordRequest(id = email, password = password)
            )
            AccountManagerUiState.Success(token = null, rawNonce = null)
        } catch (e: Exception) {
            AccountManagerUiState.Error(message = String())
        }
    }

    suspend fun signIn(): AccountManagerUiState {
        return try {
            val credentialResponse = credentialManager.getCredential(
                context = activity,
                request = GetCredentialRequest(
                    credentialOptions = listOf(GetPasswordOption())
                )
            )

            val credential = credentialResponse.credential as? PasswordCredential
                ?: return AccountManagerUiState.Error(String())

            val user = User(email = credential.id, password = credential.password)

            AccountManagerUiState.Success(user = user)
        } catch (e: Exception) {
            AccountManagerUiState.Error(message = String())
        }
    }

    private fun getFormattedNonce(rawNonce: String): String {
        val bytes = rawNonce.toByteArray()
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val digest = messageDigest.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}