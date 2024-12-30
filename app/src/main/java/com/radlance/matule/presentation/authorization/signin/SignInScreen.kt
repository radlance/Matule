package com.radlance.matule.presentation.authorization.signin

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.authorization.common.AccountManager
import com.radlance.matule.presentation.authorization.common.AuthScaffold
import com.radlance.matule.presentation.authorization.common.AuthViewModel
import com.radlance.matule.presentation.component.BackButton
import com.radlance.matule.presentation.component.EnterInputField
import com.radlance.matule.presentation.component.NavigationButton
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.theme.secondaryTextColor

@Composable
fun SignInScreen(
    onIconBackPressed: () -> Unit,
    onSignUpTextClicked: () -> Unit,
    onRecoverPasswordTextClicked: () -> Unit,
    onSuccessSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {

    var emailFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var passwordFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    val googleSignInResultUiState by viewModel.googleSignInResult.collectAsState()
    val signInResultUiState by viewModel.authResultUiState.collectAsState()
    val uiState by viewModel.authUiState.collectAsState()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val interactionSource = remember { MutableInteractionSource() }
    val snackBarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val accountManager = remember { AccountManager(context as ComponentActivity) }


    LaunchedEffect(Unit) {
        val savedPasswordUiState = accountManager.signIn()
        savedPasswordUiState.show(
            onSuccess = { _, _, user ->
                user?.let {
                    emailFieldValue = it.email
                    passwordFieldValue = it.password
                    viewModel.signIn(email = it.email, password = it.password)
                }
            },
            onError = {}
        )
    }

    signInResultUiState.Show(
        onSuccessResult = onSuccessSignIn,
        onChangeButtonState = viewModel::updateActionButtonState,
        snackBarHostState = snackBarHostState
    )

    AuthScaffold(snackBarHostState = snackBarHostState) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
                .safeDrawingPadding()
                .padding(start = 20.dp, end = 20.dp, top = 66.dp, bottom = 47.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            BackButton(onClick = onIconBackPressed, modifier = Modifier.align(Alignment.Start))

            Text(
                text = stringResource(R.string.hello),
                fontSize = 32.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Bold,
                lineHeight = 38.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 11.dp)
            )
            Text(
                text = stringResource(R.string.fill_your_data),
                fontSize = 16.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
                color = secondaryTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            EnterInputField(
                label = stringResource(R.string.email),
                value = emailFieldValue,
                onValueChange = {
                    emailFieldValue = it
                    viewModel.resetEmailError()
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 30.dp),
                isError = !uiState.isValidEmail,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                isPassword = false,
                interactionSource = interactionSource
            )

            EnterInputField(
                label = stringResource(R.string.password),
                value = passwordFieldValue,
                onValueChange = {
                    passwordFieldValue = it
                    viewModel.resetPasswordError()
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 30.dp),
                isError = !uiState.isValidPassword,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                isPassword = true,
                interactionSource = interactionSource
            )

            Text(
                text = stringResource(R.string.recover),
                color = secondaryTextColor,
                fontSize = 12.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                lineHeight = 16.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 12.dp)
                    .clickable(indication = null, interactionSource = interactionSource) {
                        onRecoverPasswordTextClicked()
                        keyboardController?.hide()
                    }
            )
            NavigationButton(
                stringResId = R.string.sign_in,
                onClick = {
                    viewModel.signIn(emailFieldValue, passwordFieldValue)
                    keyboardController?.hide()
                },
                enabled = uiState.isEnabledButton,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )

            Spacer(Modifier.height(24.dp))

            GoogleSignInButton(
                activity = (context as ComponentActivity),
                coroutineScope = coroutineScope,
                onSignIn = viewModel::signInWithGoogle,
                onSuccessSignIn = onSuccessSignIn,
                authResultUiState = googleSignInResultUiState,
                accountManager = accountManager,
                snackBarHostState = snackBarHostState
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(Modifier.padding(top = 24.dp)) {
                Text(
                    text = stringResource(R.string.is_first_time),
                    color = secondaryTextColor,
                    fontSize = 16.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 1.sp
                )
                Text(
                    text = stringResource(R.string.create_user),
                    fontSize = 16.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 1.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) {
                        onSignUpTextClicked()
                        keyboardController?.hide()
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen({}, {}, {}, {})
}

@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
private fun SignInScreenExpandedPreview() {
    SignInScreen({}, {}, {}, {})
}