package com.radlance.matule.presentation.cart

import androidx.compose.foundation.background
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
import com.radlance.matule.domain.product.Product
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun CartItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .height(104.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.width(9.dp))
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
                        .data(product.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "shoe_example",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.width(30.dp))

            Column {
                Text(
                    text = product.title,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = product.price.toString(),
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun CartItemPreview() {
    MatuleTheme {
        CartItem(
            product = Product(
                id = 1,
                title = "mock",
                description = "",
                price = 100.00,
                imageUrl = "https://",
                isFavorite = true,
                quantityInCart = 1,
                categoryId = 1
            )
        )
    }
}