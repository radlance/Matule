package com.radlance.matule.presentation.profile

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.EnterInputField
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun ProfileDataColumn(
    nameFieldValue: String,
    onNameValueChange: (String) -> Unit,
    lastNameFieldValue: String,
    onLastNameValueChange: (String) -> Unit,
    addressFieldValue: String,
    onAddressValueChange: (String) -> Unit,
    phoneNumberFieldValue: String,
    onPhoneNumberValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        EnterInputField(
            value = nameFieldValue,
            label = stringResource(R.string.name),
            onValueChange = onNameValueChange,
            hintResId = R.string.name_hint,
            interactionSource = interactionSource,
            showCheck = nameFieldValue.isNotEmpty()
        )

        EnterInputField(
            value = lastNameFieldValue,
            label = stringResource(R.string.last_name),
            onValueChange = onLastNameValueChange,
            hintResId = R.string.name_hint,
            interactionSource = interactionSource,
            showCheck = lastNameFieldValue.isNotEmpty()
        )

        EnterInputField(
            value = addressFieldValue,
            label = stringResource(R.string.address),
            onValueChange = onAddressValueChange,
            hintResId = R.string.name_hint,
            interactionSource = interactionSource,
            showCheck = addressFieldValue.isNotEmpty()
        )

        EnterInputField(
            value = phoneNumberFieldValue,
            label = stringResource(R.string.phone),
            onValueChange = onPhoneNumberValueChange,
            hintResId = R.string.name_hint,
            interactionSource = interactionSource,
            showCheck = phoneNumberFieldValue.isNotEmpty(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        )
    }
}

@Preview
@Composable
private fun ProfileDataColumnPreview() {
    MatuleTheme {
        ProfileDataColumn(
            nameFieldValue = "Emmanuel",
            onNameValueChange = {},
            lastNameFieldValue = "Oyiboke",
            onLastNameValueChange = {},
            addressFieldValue = "Nigeria",
            onAddressValueChange = {},
            phoneNumberFieldValue = "+234-811-732-5298",
            onPhoneNumberValueChange = {}
        )
    }
}