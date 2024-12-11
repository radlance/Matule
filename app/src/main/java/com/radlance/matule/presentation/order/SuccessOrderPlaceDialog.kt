package com.radlance.matule.presentation.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.lightBlueColor
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun SuccessOrderPlaceDialog(
    navigateToCatalog: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp, horizontal = 50.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(134.dp)
                    .clip(CircleShape)
                    .background(lightBlueColor)
            ) {
                Image(
                    painter = painterResource(R.drawable.success_order_place),
                    contentDescription = "success_order_place",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                )
            }
            Spacer(Modifier.height(24.dp))
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.place_order_success),
                fontSize = 20.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 28.sp
            )
            Spacer(Modifier.height(30.dp))

            Button(
                onClick = navigateToCatalog,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(51.dp)
            ) {
                Text(
                    text = stringResource(R.string.back_to_shopping),
                    fontSize = 16.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 19.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun SuccessOrderPlaceDialogPreview() {
    MatuleTheme {
        SuccessOrderPlaceDialog({})
    }
}