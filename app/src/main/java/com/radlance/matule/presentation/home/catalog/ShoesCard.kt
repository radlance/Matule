package com.radlance.matule.presentation.home.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.radlance.matule.domain.home.Product
import com.radlance.matule.presentation.component.PriceRow
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.fillRedColor
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.vector.LikeIcon

@Composable
fun ShoesCard(
    product: Product,
    onLikeClick: () -> Unit,
    onCartClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(interactionSource = interactionSource, indication = null) {
                onCardClick(product.id)
            }
    ) {
        Column {
            IconButton(
                onClick = onLikeClick,
                modifier = Modifier
                    .padding(start = 9.dp, top = 3.dp)
                    .clip(CircleShape)
                    .size(28.dp)
                    .background(MaterialTheme.colorScheme.surfaceTint),
            ) {
                val fillColor = if (product.isFavorite) {
                    fillRedColor
                } else {
                    Color.LightGray
                }

                Image(
                    imageVector = LikeIcon(fillColor = fillColor),
                    contentDescription = "LikeIcon"
                )
            }

            AsyncImage(
                ImageRequest.Builder(context = LocalContext.current)
                    .data(product.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "shoe_example",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(horizontal = 21.dp)
            )

            Text(
                text = "BEST SELLER",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                modifier = Modifier.padding(start = 9.dp)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = product.title,
                color = inputFieldTextColor,
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                modifier = Modifier.padding(start = 9.dp)
            )

            PriceRow(
                price = product.price.toString(),
                onCartClicked = onCartClick,
                inCart = product.inCart,
                contentDescription = "Add"
            )
        }
    }
}


@Preview
@Composable
private fun ShoesCardPreview() {
    MatuleTheme {
        ShoesCard(
            onLikeClick = {},
            onCartClick = {},
            modifier = Modifier.width(160.dp),
            product = Product(
                title = "mock",
                price = 100.00,
                description = "",
                imageUrl = "https://",
                isFavorite = true,
                inCart = true,
                categoryId = 1
            ),
            onCardClick = {}
        )
    }
}