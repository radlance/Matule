package com.radlance.matule.presentation.home.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.common.ProductViewModel
import com.radlance.matule.presentation.component.ProductGrid
import com.radlance.matule.presentation.home.common.ChangeProductStatus
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun PopularScreen(
    onBackPressed: () -> Unit,
    navigateToFavorite: () -> Unit,
    navigateToDetails: (Int) -> Unit,
    navigateToCart: () -> Unit,
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val fetchResult by productViewModel.catalogContent.collectAsState()
    val addToFavoriteResult by productViewModel.favoriteResult.collectAsState()
    val addToCartResult by productViewModel.inCartResult.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(dimensionResource(R.dimen.main_top_padding)))

        addToFavoriteResult.Show(
            onSuccess = {},
            onLoading = { productId ->
                ChangeProductStatus(productId, productViewModel::changeStateFavoriteStatus)
            },
            onError = { productId ->
                ChangeProductStatus(productId, productViewModel::changeStateFavoriteStatus)
            },
            onUnauthorized = {}
        )

        addToCartResult.Show(
            onSuccess = {},
            onLoading = { productId ->
                ChangeProductStatus(productId, productViewModel::changeStateInCartStatus)
            },
            onError = { productId ->
                ChangeProductStatus(
                    productId = productId,
                    onStatusChanged = {
                        productViewModel.changeStateInCartStatus(
                            it,
                            recover = true
                        )
                    }
                )
            },
            onUnauthorized = {}
        )

        fetchResult.Show(
            onSuccess = { fetchContent ->
                PopularHeader(
                    onBackPressed = onBackPressed,
                    navigateToFavorite = navigateToFavorite
                )
                Spacer(Modifier.height(20.dp))

                if (fetchContent.products.isEmpty()) {

                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = stringResource(R.string.no_popular_products),
                            fontSize = 16.sp,
                            fontFamily = ralewayFamily,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 20.sp,
                            modifier = Modifier.offset(y = (-55).dp)
                        )
                    }
                } else {
                    ProductGrid(
                        products = fetchContent.products,
                        onLikeClicked = productViewModel::changeFavoriteStatus,
                        onAddToCartClick = productViewModel::addProductToCart,
                        onCardClick = navigateToDetails,
                        onNavigateToCart = navigateToCart
                    )
                }
            },
            onError = {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = stringResource(R.string.load_error))
                        Spacer(Modifier.height(12.dp))
                        Button(onClick = productViewModel::fetchContent) {
                            Text(stringResource(R.string.retry), color = Color.White)
                        }
                    }
                }
            },

            onLoading = {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.offset(y = (-55).dp))
                }
            },

            onUnauthorized = {}
        )
    }
}

@Preview
@Composable
private fun PopularScreenPreview() {
    MatuleTheme {
        PopularScreen({}, {}, {}, {})
    }
}