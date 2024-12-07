package com.radlance.matule.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.domain.home.Product
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.poppinsFamily

@Composable
fun CartProductColumn(
    products: List<Product>,
    onChangeQuantityClick: (productId: Int, quantity: Int, increment: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Text(
                text = getProductCountText(products.size),
                fontSize = 16.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp
            )
        }

        items(items = products, key = { product -> product.id }) { product ->
            CartItemContainer(
                product = product,
                onIncrement = {
                    onChangeQuantityClick(
                        product.id,
                        product.quantityInCart.inc(),
                        true
                    )
                },
                onDecrement = {
                    onChangeQuantityClick(
                        product.id,
                        product.quantityInCart.dec(),
                        false
                    )
                },

                onRemove = {}

            ) { item ->
                CartItem(
                    product = item,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

private fun getProductCountText(count: Int): String {
    return when {
        count % 10 == 1 && count % 100 != 11 -> "$count Товар"
        count % 10 in 2..4 && (count % 100 < 12 || count % 100 > 14) -> "$count Товара"
        else -> "$count Товаров"
    }
}

@Preview(showBackground = true)
@Composable
private fun CartProductColumnPreview() {
    MatuleTheme {
        CartProductColumn(
            onChangeQuantityClick = { _, _, _ -> },
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
            }
        )
    }
}