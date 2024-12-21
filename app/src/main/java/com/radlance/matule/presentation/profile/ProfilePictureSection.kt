package com.radlance.matule.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun ProfilePictureSection(
    name: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
        )
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
            lineHeight = 16.sp
        )
    }
}

@Preview
@Composable
private fun ProfilePictureSectionPreview() {
    MatuleTheme {
        ProfilePictureSection("name")
    }
}