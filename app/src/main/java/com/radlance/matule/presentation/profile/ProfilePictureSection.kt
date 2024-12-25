package com.radlance.matule.presentation.profile

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.radlance.matule.R
import com.radlance.matule.presentation.common.FetchResultUiState
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun ProfilePictureSection(
    name: String,
    imageUrl: String,
    uploadImageResult: FetchResultUiState<String>,
    onLoadImageResult: (imageByteArray: ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var imageState by rememberSaveable { mutableStateOf<Uri>(Uri.EMPTY) }
    var showProgressBar by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(imageUrl) {
        imageState = Uri.parse(imageUrl)
    }

    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                val imageByteArray = uri.toByteArray(context)
                imageByteArray?.let { onLoadImageResult(it) }
            }
        }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        uploadImageResult.Show(
            onSuccess = {
                showProgressBar = false
                imageState = Uri.parse(it)
            },
            onError = {
                showProgressBar = false
                LaunchedEffect(Unit) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_save_image),
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            onLoading = {
                showProgressBar = true
            }
        )
        if (!showProgressBar) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageState)
                    .crossfade(true)
                    .build(),

                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop

            )
        } else {
            CircularProgressIndicator(modifier = Modifier.size(96.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            fontSize = 20.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 23.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.edit_profile),
            fontSize = 12.sp,
            fontFamily = ralewayFamily,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 16.sp,
            modifier = Modifier.clickable { resultLauncher.launch("image/*") }
        )
    }
}

private fun Uri.toByteArray(context: Context): ByteArray? {
    return context.contentResolver.openInputStream(this)?.use { it.buffered().readBytes() }
}

@Preview
@Composable
private fun ProfilePictureSectionPreview() {
    MatuleTheme {
        ProfilePictureSection(
            name = "name",
            onLoadImageResult = {},
            imageUrl = "",
            uploadImageResult = FetchResultUiState.Initial()
        )
    }
}