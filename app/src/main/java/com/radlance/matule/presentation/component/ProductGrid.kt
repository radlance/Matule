package com.radlance.matule.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.domain.product.Product
import com.radlance.matule.presentation.home.core.ShoesCard
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun ProductGrid(
    products: List<Product>,
    onLikeClicked: (productId: Int) -> Unit,
    onAddToCartClick: (productId: Int) -> Unit,
    onCardClick: (productId: Int) -> Unit,
    onNavigateToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 21.dp, end = 21.dp, top = 20.dp, bottom = 140.dp),
        horizontalArrangement = Arrangement.spacedBy(13.dp),
        verticalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        items(items = products, key = { product -> product.id }) { product ->
            ShoesCard(
                product = product,
                onLikeClick = { onLikeClicked(product.id) },
                onCartClick = {
                    if (product.quantityInCart == 0) {
                        onAddToCartClick(product.id)
                    } else {
                        onNavigateToCart()
                    }
                },
                onCardClick = onCardClick
            )
        }
    }
}

@Preview
@Composable
private fun ProductGridPreview() {
    MatuleTheme {
        ProductGrid(
            products = List(20) {
                Product(
                    id = it,
                    title = "mock$it",
                    description = "",
                    price = 100.00 * it,
                    imageUrl = "https://",
                    isFavorite = true,
                    quantityInCart = 1,
                    categoryId = 1
                )
            },
            onLikeClicked = {},
            onAddToCartClick = {},
            onCardClick = {},
            onNavigateToCart = {}
        )
    }
}