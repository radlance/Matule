package com.radlance.matule.presentation.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.brightBlueColor
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun OrderAddress(
    address: String,
    onMapClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column {

            Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = address,
                    color = inputFieldTextColor,
                    fontSize = 12.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp
                )
            }
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.ExpandMore,
                "ExpandMore",
                tint = inputFieldTextColor,
                modifier = Modifier.align(Alignment.Bottom)
            )
        }

        Spacer(Modifier.height(16.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(101.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.map_example),
                contentDescription = "map_example",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .clickable { onMapClick() }
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.see_on_map),
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 23.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(brightBlueColor)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_location),
                        contentDescription = "ic_location"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OrderAddressPreview() {
    MatuleTheme {
        OrderAddress(address = "1082 Аэропорт, Нигерии", onMapClick = {})
    }
}