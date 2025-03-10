package com.radlance.matule.presentation.home.details

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.radlance.matule.R
import com.radlance.matule.domain.product.Product
import com.radlance.matule.ui.theme.MatuleTheme


@Composable
fun AdditionalRowItem(
    product: Product,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val backgroundColor by animateColorAsState(
        if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }, label = "backgroundColor"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(product.imageUrl)
                .crossfade(true)
                .build(),
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
            contentDescription = "additional row item"
        )
    }
}

@Preview
@Composable
private fun AdditionalRowItemPreview() {
    MatuleTheme {
        AdditionalRowItem(
            product = Product(
                title = "mock",
                price = 100.00,
                description = "",
                imageUrl = "https://",
                isFavorite = true,
                quantityInCart = 1,
                categoryId = 1
            ),
            selected = true
        )
    }
}