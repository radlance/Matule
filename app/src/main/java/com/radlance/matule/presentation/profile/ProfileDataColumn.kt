package com.radlance.matule.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun ProfileDataColumn(
    name: String,
    lastName: String,
    address: String,
    phoneNumber: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        ProfileDataSection(labelResId = R.string.name, value = name)
        ProfileDataSection(labelResId = R.string.last_name, value = lastName)
        ProfileDataSection(labelResId = R.string.address, value = address)
        ProfileDataSection(labelResId = R.string.phone, value = phoneNumber)
    }
}

@Preview
@Composable
private fun ProfileDataColumnPreview() {
    MatuleTheme {
        ProfileDataColumn(
            name = "Emmanuel",
            lastName = "Oyiboke",
            address = "Nigeria",
            phoneNumber = "+234-811-732-5298"
        )
    }
}