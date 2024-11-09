package com.radlance.matule.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun OnBoardingButton(
    @StringRes stringResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 36.dp, top = 16.dp)
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(13.dp),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Text(
            text = stringResource(stringResId),
            fontSize = 14.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.SemiBold
        )
    }

}