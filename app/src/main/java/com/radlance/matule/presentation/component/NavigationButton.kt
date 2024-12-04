package com.radlance.matule.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.ui.theme.bluePrimaryColor
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun NavigationButton(
    @StringRes stringResId: Int,
    onClick: () -> Unit,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors().copy(
        containerColor = bluePrimaryColor,
        contentColor = Color.White
    ),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            icon?.let {
                Icon(imageVector = it, contentDescription = "button icon")
                Spacer(Modifier.width(16.dp))
            }
            Text(
                text = stringResource(stringResId),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}