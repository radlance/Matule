package com.radlance.matule.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.ui.theme.blueButtonColor
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun NavigationButton(
    @StringRes stringResId: Int,
    onClick: () -> Unit,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors().copy(
        containerColor = blueButtonColor,
        contentColor = Color.White
    ),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = {
            onClick()
        },
        enabled = enabled,
        modifier = modifier
            .height(50.dp),
        shape = RoundedCornerShape(13.dp),
        colors = buttonColors
    ) {
        Text(
            text = stringResource(stringResId),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.SemiBold
        )
    }
}