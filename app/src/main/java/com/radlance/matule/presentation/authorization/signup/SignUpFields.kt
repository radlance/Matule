package com.radlance.matule.presentation.authorization.signup

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.R
import com.radlance.matule.presentation.authorization.common.AuthUiState
import com.radlance.matule.presentation.component.EnterInputField
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun SignUpFields(
    nameFieldValue: String,
    onNameValueChange: (String) -> Unit,
    emailFieldValue: String,
    onEmailValueChange: (String) -> Unit,
    passwordFieldValue: String,
    onPasswordValueChange: (String) -> Unit,
    uiState: AuthUiState,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = MutableInteractionSource()
) {
    Column(verticalArrangement = Arrangement.spacedBy(30.dp), modifier = modifier.fillMaxWidth()) {
        EnterInputField(
            label = stringResource(R.string.your_name),
            value = nameFieldValue,
            onValueChange = onNameValueChange,
            modifier = Modifier
                .align(Alignment.Start),
            isPassword = false,
            isError = !uiState.isValidName,
            interactionSource = interactionSource
        )

        EnterInputField(
            label = stringResource(R.string.email),
            value = emailFieldValue,
            onValueChange = onEmailValueChange,
            modifier = Modifier
                .align(Alignment.Start),
            isPassword = false,
            isError = !uiState.isValidEmail,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            interactionSource = interactionSource
        )

        EnterInputField(
            label = stringResource(R.string.password),
            value = passwordFieldValue,
            onValueChange = onPasswordValueChange,
            modifier = Modifier
                .align(Alignment.Start),
            isPassword = true,
            isError = !uiState.isValidPassword,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            interactionSource = interactionSource
        )
    }
}

@Preview
@Composable
private fun SignUpFieldsPreview() {
    MatuleTheme {
        SignUpFields(
            nameFieldValue = "",
            onNameValueChange = {},
            emailFieldValue = "",
            onEmailValueChange = {},
            passwordFieldValue = "",
            onPasswordValueChange = {},
            uiState = AuthUiState()
        )
    }
}