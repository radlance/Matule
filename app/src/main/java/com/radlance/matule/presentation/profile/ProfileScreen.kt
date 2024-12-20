package com.radlance.matule.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(55.dp))
        ProfileHeader()
        Spacer(Modifier.height(48.dp))
        ProfilePictureSection()
        Spacer(Modifier.height(19.dp))
        Barcode(modifier = Modifier.padding(start = 15.dp, end = 20.dp))
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    MatuleTheme {
        ProfileScreen()
    }
}

@Preview(device = "spec:width=673dp,height=841dp")
@Composable
private fun ProfileScreenPreviewExpanded() {
    MatuleTheme {
        ProfileScreen()
    }
}