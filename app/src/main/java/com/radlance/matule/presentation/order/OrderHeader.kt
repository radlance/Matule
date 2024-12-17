package com.radlance.matule.presentation.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.BackButton
import com.radlance.matule.presentation.component.CommonHeader
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun OrderHeader(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    CommonHeader(
        startContent = {
            BackButton(
                onClicked = onBackPressed,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            )
        },
        middleContent = {
            Text(
                text = stringResource(R.string.favorite),
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp
            )
        },
        horizontalArrangement = Arrangement.SpaceBetween,
        endContent = {
            Box(Modifier.size(44.dp))
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp)
    )
}