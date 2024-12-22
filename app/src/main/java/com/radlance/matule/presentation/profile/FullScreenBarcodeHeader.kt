package com.radlance.matule.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.BackButton
import com.radlance.matule.presentation.component.CommonHeader
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun FullScreenBarcodeHeader(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    CommonHeader(
        modifier = modifier
            .fillMaxWidth(),

        startContent = {
            BackButton(
                onClick = onBackPressed,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            )
        },
        middleContent = {
            Text(
                text = stringResource(R.string.profile),
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )
        },
        endContent = {
            Box(modifier = Modifier.size(44.dp))
        }
    )
}

@Preview
@Composable
private fun FullScreenBarcodeHeaderPreview() {
    MatuleTheme {
        FullScreenBarcodeHeader({})
    }
}