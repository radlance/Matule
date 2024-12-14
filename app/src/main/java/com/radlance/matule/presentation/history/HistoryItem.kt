package com.radlance.matule.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun HistoryItem(
    historyProduct: HistoryProduct,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(105.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row {
            Spacer(Modifier.width(9.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(87.dp)
                    .fillMaxHeight()
                    .padding(top = 9.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceTint)
            ) {
                AsyncImage(
                    ImageRequest.Builder(context = LocalContext.current)
                        .data(historyProduct.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "shoe_example",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.width(15.dp))
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = historyProduct.title,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp
                )

                Text(
                    text = "₽${historyProduct.price}",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )

            }
            Spacer(Modifier.weight(1f))

            Text(
                text = historyProduct.orderTime.toLocalDateTime(TimeZone.UTC).date.toString(),
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                color = inputFieldTextColor,
                lineHeight = 20.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
            Spacer(Modifier.width(10.dp))
        }
    }
}

@Preview
@Composable
private fun HistoryItemPreview() {
    MatuleTheme {
        HistoryItem(
            historyProduct = HistoryProduct(
                id = 1,
                title = "mock",
                price = 100.0,
                imageUrl = "https://",
                orderTime = Clock.System.now()
            ),
        )
    }
}