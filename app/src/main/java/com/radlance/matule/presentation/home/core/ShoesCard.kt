package com.radlance.matule.presentation.home.core

import androidx.compose.animation.animateContentSize
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.radlance.matule.R
import com.radlance.matule.domain.product.Product
import com.radlance.matule.presentation.component.PriceRow
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.fillRedColor
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.vector.FavoriteIcon

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
                    .background(MaterialTheme.colorScheme.surfaceVariant),
            ) {
                val fillColor = if (product.isFavorite) {
                    fillRedColor
                } else {
                    Color.LightGray
                }

                Image(
                    imageVector = FavoriteIcon(fillColor = fillColor),
                    contentDescription = "LikeIcon"
                )
            }

            SubcomposeAsyncImage(
                ImageRequest.Builder(context = LocalContext.current)
                    .data(product.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "shoe_example",
                contentScale = ContentScale.FillWidth,
                loading = {
                    Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                },
                error = {
                    Icon(
                        painter = painterResource(R.drawable.shoe_placeholder),
                        contentDescription
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(100.dp)
                    .animateContentSize()
                    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(
                                0f to Color.Transparent,
                                0.3f to Color.Red,
                                0.7f to Color.Red,
                                1f to Color.Transparent
                            ), blendMode = BlendMode.DstIn
                        )
                    }
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
                inCart = product.quantityInCart != 0,
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
                quantityInCart = 1,
                categoryId = 1
            ),
            onCardClick = {}
        )
    }
}