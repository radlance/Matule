package com.radlance.matule.presentation.home.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.domain.home.Product
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun PopularRow(
    products: List<Product>,
    onLikeClick: (productId: Int) -> Unit,
    onAddCartClick: (productId: Int) -> Unit,
    onCardClick: (Int) -> Unit,
    onNavigateToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 11.5.dp, end = 28.5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.popular),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp
            )

            Text(
                text = stringResource(R.string.all),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 20.dp)
        ) {
            if(products.isNotEmpty()) {
                with(products.first()) {
                    ShoesCard(
                        onLikeClick = {
                            onLikeClick(id)
                        },
                        product = this,
                        onCartClick = {
                            if (!inCart) {
                                onAddCartClick(id)
                            } else {
                                onNavigateToCart()
                            }
                        },
                        onCardClick = onCardClick,
                        modifier = modifier.weight(1f)
                    )
                }

                Spacer(Modifier.width(19.dp))

                with(products[1]) {
                    ShoesCard(
                        onLikeClick = { onLikeClick(products[1].id) },
                        product = products[1],
                        onCartClick = {
                            if (!inCart) {
                                onAddCartClick(id)
                            } else {
                                onNavigateToCart()
                            }
                        },
                        onCardClick = onCardClick,
                        modifier = modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PopularRowPreview() {
    MatuleTheme {
        PopularRow(
            emptyList(),
            onLikeClick = {},
            onAddCartClick = {},
            onNavigateToCart = {},
            onCardClick = {}
        )
    }
}