package com.radlance.matule.presentation.authorization.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.authorization.common.AuthScaffold
import com.radlance.matule.presentation.authorization.common.AuthViewModel
import com.radlance.matule.presentation.component.BackButton
import com.radlance.matule.presentation.component.EnterInputField
import com.radlance.matule.presentation.component.NavigationButton
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.theme.secondaryTextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    onBackPressed: () -> Unit,
    onSignInTextClicked: () -> Unit,
    onSuccessSignUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {

    BackHandler { onBackPressed() }

    var nameFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var emailFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var passwordFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var checked by rememberSaveable {
        mutableStateOf(false)
    }

    val signUpResultUiState by viewModel.authResultUiState.collectAsState()

    val uiState by viewModel.authUiState.collectAsState()

    val interactionSource = remember { MutableInteractionSource() }

    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val snackBarHostState = remember { SnackbarHostState() }

    val keyboardController = LocalSoftwareKeyboardController.current

    AuthScaffold(snackBarHostState = snackBarHostState, modifier = modifier) {
        signUpResultUiState.Show(
            onSuccessResult = onSuccessSignUp,
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

            BackButton(onClick = onBackPressed, modifier = Modifier.align(Alignment.Start))

            Text(
                text = stringResource(R.string.registration),
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
                label = stringResource(R.string.your_name),
                value = nameFieldValue,
                onValueChange = {
                    nameFieldValue = it
                    viewModel.resetNameError()
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 30.dp),
                isPassword = false,
                isError = !uiState.isValidName,
                interactionSource = interactionSource
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
                isPassword = false,
                isError = !uiState.isValidEmail,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
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
                isPassword = true,
                isError = !uiState.isValidPassword,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                interactionSource = interactionSource
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(18.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.surfaceTint)
                        .clickable(indication = null, interactionSource = interactionSource) {
                            checked = !checked
                        }
                ) {
                    if (checked) {
                        Image(
                            painter = painterResource(R.drawable.ic_agreement),
                            contentDescription = "ic_agreement"
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.agreement),
                    fontSize = 16.sp,
                    color = secondaryTextColor,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable {
                            val pdfUrl =
                                "https://drive.google.com/uc?id=1UD2e1VZKOHcXqrHlH65PBhPAXIftbg07"
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                setDataAndType(Uri.parse(pdfUrl), "application/pdf")
                                addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                            }
                            context.startActivity(intent)
                            keyboardController?.hide()
                        }
                )
            }

            NavigationButton(
                stringResId = R.string.register,
                onClick = {
                    viewModel.signUp(
                        nameFieldValue,
                        emailFieldValue,
                        passwordFieldValue
                    )

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