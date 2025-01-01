package com.radlance.matule.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun ProfileScreen(
    onBarcodeClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val userData by profileViewModel.userData.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(55.dp))
        ProfileHeader()
        Spacer(Modifier.height(48.dp))

        userData.Show(
            onSuccess = { userData ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfilePictureSection(
                        name = userData.name,
                        imageUrl = userData.imageUrl,
                        onEditProfileClick = onEditProfileClick
                    )

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
            },
            onError = {},
            onLoading = {},
            onUnauthorized = {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.offset(y = (-55).dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = onSignInClick) {
                            Text(text = stringResource(R.string.sign_in))
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    MatuleTheme {
        ProfileScreen({}, {}, {})
    }
}

@Preview(device = "spec:width=673dp,height=841dp")
@Composable
private fun ProfileScreenPreviewExpanded() {
    MatuleTheme {
        ProfileScreen({}, {}, {})
    }
}