package com.radlance.matule.presentation.authorization.signin

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.radlance.matule.BuildConfig
import com.radlance.matule.R
import com.radlance.matule.presentation.authorization.common.AuthResultUiState
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.secondaryTextColor
import com.radlance.matule.ui.vector.GoogleIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID

@Composable
fun GoogleSignInButton(
    context: Context,
    coroutineScope: CoroutineScope,
    onSignIn: (googleIdToken: String, rawNonce: String) -> Unit,
    onSuccessSignIn: () -> Unit,
    authResultUiState: AuthResultUiState,
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState = SnackbarHostState()
) {
    var continueSignIn by rememberSaveable { mutableStateOf(false) }

    if (continueSignIn) {
        authResultUiState.Show(
            onSuccessResult = onSuccessSignIn,
            onChangeButtonState = {},
            snackBarHostState = snackBarHostState
        )
    }

    val onClick: () -> Unit = {
        val credentialManager = CredentialManager.create(context)
        val rawNonce = UUID.randomUUID().toString()

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(BuildConfig.SERVER_CLIENT_ID)
            .setNonce(getFormattedNonce(rawNonce))
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        coroutineScope.launch {
            try {
                val result = credentialManager.getCredential(request = request, context = context)
                val credential = result.credential
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val googleIdToken = googleIdTokenCredential.idToken

                onSignIn(googleIdToken, rawNonce)

                continueSignIn = true
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    context.getString(R.string.sign_in_error),
                    Toast.LENGTH_SHORT
                ).show()
                continueSignIn = false
            }
        }
    }

    IconButton(
        onClick = onClick,
        modifier = modifier.border(
            1.5.dp,
            color = secondaryTextColor,
            shape = CircleShape
        )
    ) {
        Image(
            GoogleIcon,
            contentDescription = "GoogleSignInButton",
            modifier = Modifier.scale(0.7f)
        )
    }
}

private fun getFormattedNonce(rawNonce: String): String {
    val bytes = rawNonce.toByteArray()
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val digest = messageDigest.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}

@Preview
@Composable
private fun GoogleSignInButtonPreview() {
    MatuleTheme {
        GoogleSignInButton(
            context = LocalContext.current,
            onSignIn = { _, _ -> },
            authResultUiState = AuthResultUiState.Initial,
            coroutineScope = CoroutineScope(Dispatchers.Main),
            onSuccessSignIn = {}
        )
    }
}