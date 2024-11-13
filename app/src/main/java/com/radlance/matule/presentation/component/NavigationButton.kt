package com.radlance.matule.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun NavigationButton(
    @StringRes stringResId: Int,
    onClick: () -> Unit,
    buttonColors: ButtonColors,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(13.dp),
        colors = buttonColors
    ) {
        Text(
            text = stringResource(stringResId),
            fontSize = 14.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.SemiBold
        )
    }
}