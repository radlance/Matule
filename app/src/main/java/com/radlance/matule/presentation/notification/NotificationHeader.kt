package com.radlance.matule.presentation.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.CommonHeader
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun NotificationHeader(modifier: Modifier = Modifier) {
    CommonHeader(
        startContent = {},
        middleContent = {
            Text(
                text = stringResource(R.string.notifications),
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp
            )
        },
        endContent = {},
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    )
}

@Preview
@Composable
private fun NotificationHeaderPreview() {
    MatuleTheme {
        NotificationHeader()
    }
}