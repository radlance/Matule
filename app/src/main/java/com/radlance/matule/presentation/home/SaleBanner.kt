package com.radlance.matule.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.futuraFamily
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.theme.salesColor

@Composable
fun SaleBanner(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.sales),
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 19.sp
            )

            Text(
                text = "Все",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp
            )
        }
        Spacer(Modifier.height(21.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Image(
                painter = painterResource(R.drawable.home_highlight_1),
                contentDescription = "home_highlight_1",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 18.dp, start = 8.dp)
            )

            Highlight(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 4.dp, end = 40.dp)
                    .alpha(0.5f)
            )

            Highlight(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp, start = 35.dp)
                    .alpha(0.4f)
            )

            Highlight(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp, start = 35.dp)
                    .alpha(0.3f)
            )

            Highlight(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 15.dp, end = 10.dp)
                    .alpha(0.5f)
            )

            Column(
                modifier = Modifier
                    .padding(start = 22.dp)
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = "Summer Sale",
                    fontSize = 12.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 14.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.sale_15_off),
                    color = salesColor,
                    fontSize = 36.sp,
                    fontFamily = futuraFamily,
                    fontWeight = FontWeight(900),
                    lineHeight = 36.sp
                )
            }

            Icon(
                painter = painterResource(R.drawable.home_new_pointer),
                contentDescription = "home_new_pointer",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 12.dp, start = 90.dp)
            )

            Image(
                painter = painterResource(R.drawable.home_sale_shoe),
                contentDescription = "home_sale_shoe",
                modifier = Modifier
                    .padding(top = 5.dp, start = 170.dp)
                    .height(77.dp)
                    .width(86.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
private fun Highlight(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.home_highlight_1),
        contentDescription = "home_highlight_1",
        modifier = modifier
    )
}

@Preview
@Composable
private fun SaleBannerPreview() {
    MatuleTheme {
        SaleBanner()
    }
}