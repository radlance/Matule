package com.radlance.matule.presentation.profile.edit

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.EnterInputField
import com.radlance.matule.presentation.component.NavigationButton
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun EditProfileFields(
    name: String,
    email: String,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    profileUiState: ProfileUiState,
    modifier: Modifier = Modifier
) {


    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier.fillMaxWidth()) {
        EnterInputField(
            value = name,
            onValueChange = { onNameChanged(it) },
            label = stringResource(R.string.your_name),
            hintResId = R.string.name_hint,
            interactionSource = interactionSource,
            isError = !profileUiState.isValidName
        )

        Spacer(Modifier.height(12.dp))
        EnterInputField(
            value = email,
            enabled = false,
            onValueChange = { onEmailChanged(it) },
            hintResId = R.string.email_hint,
            label = stringResource(R.string.email),
            interactionSource = interactionSource,
            isError = !profileUiState.isValidEmail
        )

        Spacer(Modifier.height(30.dp))

        NavigationButton(
            stringResId = R.string.save,
            onClick = onSaveClick,
            enabled = profileUiState.isEnabledButton,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun EditProfileFieldsPreview() {
    MatuleTheme {
        EditProfileFields(
            "name",
            email = "email",
            onNameChanged = {},
            onEmailChanged = {},
            onSaveClick = {},
            profileUiState = ProfileUiState()
        )
    }
}