package com.radlance.matule.presentation.authorization.signup

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.authorization.common.GoogleAccountManager
import com.radlance.matule.presentation.authorization.common.AuthScaffold
import com.radlance.matule.presentation.authorization.common.AuthViewModel
import com.radlance.matule.presentation.component.NavigationButton
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.theme.secondaryTextColor
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    onBackPressed: () -> Unit,
    onSignInTextClicked: () -> Unit,
    onSuccessSignUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {

    BackHandler { onBackPressed() }

    var nameFieldValue by rememberSaveable { mutableStateOf("") }
    var emailFieldValue by rememberSaveable { mutableStateOf("") }
    var passwordFieldValue by rememberSaveable { mutableStateOf("") }
    var checked by rememberSaveable { mutableStateOf(false) }

    val signUpResultUiState by viewModel.authResultUiState.collectAsState()
    val uiState by viewModel.authUiState.collectAsState()

    val interactionSource = remember { MutableInteractionSource() }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val googleAccountManager = GoogleAccountManager(context as ComponentActivity)
    val coroutineScope = rememberCoroutineScope()

    val pdfReader = PdfReader(context)

    AuthScaffold(snackBarHostState = snackBarHostState, modifier = modifier) {
        signUpResultUiState.Show(
            onSuccessResult = {
                onSuccessSignUp()
                coroutineScope.launch {
                    googleAccountManager.signUp(emailFieldValue, passwordFieldValue)
                }
            },
            onChangeButtonState = viewModel::updateActionButtonState,
            snackBarHostState = snackBarHostState
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
                .safeDrawingPadding()
                .padding(start = 20.dp, end = 20.dp, top = 66.dp, bottom = 47.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SignUpTitle(onBackPressed = onBackPressed, modifier = Modifier.align(Alignment.Start))
            Spacer(Modifier.height(30.dp))
            SignUpFields(
                nameFieldValue = nameFieldValue,
                onNameValueChange = {
                    nameFieldValue = it
                    viewModel.resetNameError()
                },
                emailFieldValue = emailFieldValue,
                onEmailValueChange = {
                    emailFieldValue = it
                    viewModel.resetEmailError()
                },
                passwordFieldValue = passwordFieldValue,
                onPasswordValueChange = {
                    passwordFieldValue = it
                    viewModel.resetPasswordError()
                },
                uiState = uiState,
                interactionSource = interactionSource
            )

            Spacer(Modifier.height(8.dp))

            AgreementRow(
                onSwitchCheckBox = {
                    checked = !checked
                },
                onPdfLinkClick = {
                    val pdfUrl = "https://drive.google.com/uc?id=1UD2e1VZKOHcXqrHlH65PBhPAXIftbg07"
                    pdfReader.readPdf(downloadUrl = pdfUrl)
                },
                checked = checked,
                interactionSource = interactionSource
            )

            NavigationButton(
                stringResId = R.string.register,
                onClick = {
                    viewModel.signUp(nameFieldValue, emailFieldValue, passwordFieldValue)
                    keyboardController?.hide()
                },
                enabled = checked && uiState.isEnabledButton,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(modifier = Modifier.padding(top = 24.dp)) {
                Text(
                    text = stringResource(R.string.have_an_account),
                    color = secondaryTextColor,
                    fontSize = 16.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 1.sp
                )
                Text(
                    text = stringResource(R.string.sign_in),
                    fontSize = 16.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 1.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) {
                        onSignInTextClicked()
                        keyboardController?.hide()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    MatuleTheme {
        SignUpScreen({}, {}, {})
    }
}

@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
private fun SignUpScreenExpandedPreview() {
    MatuleTheme {
        SignUpScreen({}, {}, {})
    }
}