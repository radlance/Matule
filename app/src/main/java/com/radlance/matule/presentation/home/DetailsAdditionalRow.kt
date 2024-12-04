package com.radlance.matule.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.domain.home.Product
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun DetailsAdditionalRow(
    otherProducts: List<Product>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(items = otherProducts, key = { product -> product.id }) { product ->
            AdditionalRowItem(product = product, modifier = Modifier.padding(7.dp))
        }
    }
}

@Preview
@Composable
private fun DetailsAdditionalRowPreview() {
    MatuleTheme {
        DetailsAdditionalRow(
            otherProducts = List(20) {
                Product(
                    id = it,
                    title = "mock$it",
                    description = "",
                    price = 100.00 * it,
                    imageUrl = "https://",
                    isFavorite = true,
                    inCart = true,
                    categoryId = 1
                )
            }
        )
    }
}
