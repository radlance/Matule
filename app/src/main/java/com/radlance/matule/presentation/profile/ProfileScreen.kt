package com.radlance.matule.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun ProfileScreen(
    onBarcodeClick: () -> Unit,
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val userData by profileViewModel.userData.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(55.dp))
        ProfileHeader()
        Spacer(Modifier.height(48.dp))
        ProfilePictureSection(name = userData.name)
        Spacer(Modifier.height(19.dp))
        Barcode(
            onBarcodeClick = onBarcodeClick,
            modifier = Modifier.padding(start = 15.dp, end = 20.dp)
        )
        ProfileDataColumn(
            name = userData.name,
            lastName = "",
            address = "Nigeria",
            phoneNumber = "+234-811-732-5298",
            modifier = Modifier.padding(start = 22.dp, end = 18.dp)
        )

        Spacer(Modifier.height(140.dp))
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    MatuleTheme {
        ProfileScreen({})
    }
}

@Preview(device = "spec:width=673dp,height=841dp")
@Composable
private fun ProfileScreenPreviewExpanded() {
    MatuleTheme {
        ProfileScreen({})
    }
}