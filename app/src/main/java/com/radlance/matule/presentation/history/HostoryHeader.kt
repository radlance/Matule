package com.radlance.matule.presentation.history

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.CommonHeader
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun HistoryHeader(modifier: Modifier = Modifier) {
    CommonHeader(
        modifier = modifier,
        startContent = {},
        middleContent = {
            Text(
                text = stringResource(R.string.orders),
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp
            )
        },
        endContent = {}
    )
}