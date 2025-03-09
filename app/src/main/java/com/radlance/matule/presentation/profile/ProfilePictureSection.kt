package com.radlance.matule.presentation.profile

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily
import androidx.core.net.toUri

@Composable
fun ProfilePictureSection(
    name: String,
    imageUrl: String,
    onEditProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var imageState by rememberSaveable { mutableStateOf<Uri>(Uri.EMPTY) }

    LaunchedEffect(imageUrl) {
        imageState = imageUrl.toUri()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
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
        modifier = Modifier.clickable { onEditProfileClick() }
    )
}


@Preview
@Composable
private fun ProfilePictureSectionPreview() {
    MatuleTheme {
        ProfilePictureSection(
            name = "name",
            onEditProfileClick = {},
            imageUrl = ""
        )
    }
}