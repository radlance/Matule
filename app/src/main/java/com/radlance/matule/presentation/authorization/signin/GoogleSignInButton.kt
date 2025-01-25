package com.radlance.matule.presentation.authorization.signin

import android.app.Activity
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import com.radlance.matule.presentation.authorization.common.GoogleAccountManager
import com.radlance.matule.presentation.authorization.common.AuthResultUiState
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.secondaryTextColor
import com.radlance.matule.ui.vector.GoogleIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GoogleSignInButton(
    activity: Activity,
    coroutineScope: CoroutineScope,
    onSignIn: (googleIdToken: String, rawNonce: String) -> Unit,
    onSuccessSignIn: () -> Unit,
    authResultUiState: AuthResultUiState,
    accountManager: AccountManager,
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
        coroutineScope.launch {
            val result = accountManager.signInWithGoogle()
            result.show(
                onSuccess = { token, rawNonce, _ ->
                    onSignIn(token, rawNonce)
                continueSignIn = true
                },

                onError = { message ->
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                continueSignIn = false
                }
            )
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

@Preview
@Composable
private fun GoogleSignInButtonPreview() {
    MatuleTheme {
        GoogleSignInButton(
            activity = (LocalContext.current as ComponentActivity),
            onSignIn = { _, _ -> },
            authResultUiState = AuthResultUiState.Initial,
            coroutineScope = CoroutineScope(Dispatchers.Main),
            accountManager = GoogleAccountManager(LocalContext.current as ComponentActivity),
            onSuccessSignIn = {}
        )
    }
}