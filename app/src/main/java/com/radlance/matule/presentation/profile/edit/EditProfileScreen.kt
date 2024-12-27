package com.radlance.matule.presentation.profile.edit

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.authorization.common.AuthScaffold
import com.radlance.matule.presentation.profile.ProfileViewModel
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun EditProfileScreen(
    onBackPressed: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val snackBarHostState = remember { SnackbarHostState() }

    val userData by viewModel.userData.collectAsState()
    val profileUiState by viewModel.profileUiState.collectAsState()

    var nameFieldValue by rememberSaveable { mutableStateOf("") }
    var emailFieldValue by rememberSaveable { mutableStateOf("") }
    var imageState by rememberSaveable { mutableStateOf<Uri>(Uri.EMPTY) }

    val updateUserResult by viewModel.updateUserResult.collectAsState()

    LaunchedEffect(userData.imageUrl) {
        if (userData.imageUrl.isNotEmpty()) {
            imageState = Uri.parse(userData.imageUrl)
        }
    }

    LaunchedEffect(userData.name) {
        nameFieldValue = userData.name
    }

    LaunchedEffect(userData.email) {
        emailFieldValue = userData.email
    }

    updateUserResult.Show(
        onSuccess = {
            LaunchedEffect(Unit) {
                onNavigateToProfile()
            }
        },

        onError = {
            LaunchedEffect(Unit) {
                snackBarHostState.showSnackbar(
                    message = context.getString(R.string.fail_data_save),
                    duration = SnackbarDuration.Indefinite,
                    withDismissAction = true
                )
            }
            viewModel.updateActionButtonState(true)
        },
        onLoading = {
            LaunchedEffect(Unit) {
                keyboardController?.hide()
                snackBarHostState.showSnackbar(
                    message = context.getString(R.string.save_data),
                    duration = SnackbarDuration.Indefinite
                )
            }
            viewModel.updateActionButtonState(false)
        }
    )

    AuthScaffold(
        snackBarHostState = snackBarHostState,
        snackBarPaddingValues = PaddingValues(bottom = 120.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
        ) {
            Spacer(Modifier.height(dimensionResource(R.dimen.main_top_padding)))
            EditProfileHeader(onBackPressed = onBackPressed)
            Spacer(Modifier.height(37.dp))
            EditProfileImage(
                currentImageUri = imageState,
                onImageChanged = { imageState = it }
            )
            Spacer(Modifier.height(22.dp))
            EditProfileFields(
                name = nameFieldValue,
                email = emailFieldValue,
                onNameChanged = {
                    nameFieldValue = it
                    viewModel.resetNameError()
                },

                onEmailChanged = {
                    emailFieldValue = it
                    viewModel.resetEmailError()
                },

                profileUiState = profileUiState,
                onSaveClick = {
                    val byteArray = if (imageState != Uri.parse(userData.imageUrl)) {
                        imageState.toByteArray(context)
                    } else {
                        null
                    }

                    viewModel.saveProfileChanges(
                        name = nameFieldValue,
                        email = emailFieldValue,
                        imageByteArray = byteArray
                    )
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(Modifier.height(140.dp))
        }
    }
}

private fun Uri.toByteArray(context: Context): ByteArray? {
    return context.contentResolver.openInputStream(this)?.use { it.buffered().readBytes() }
}

@Preview
@Composable
private fun EditProfileScreenPreview() {
    MatuleTheme {
        EditProfileScreen({}, {})
    }
}