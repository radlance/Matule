package com.radlance.matule.presentation.favorite

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.home.HomeViewModel
import com.radlance.matule.presentation.home.common.ChangeProductStatus
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun FavoriteScreen(
    onNavigateToCart: () -> Unit,
    onNavigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val catalogContent by viewModel.catalogContent.collectAsState()
    val addToFavoriteResult by viewModel.favoriteResult.collectAsState()
    val addToCartResult by viewModel.inCartResult.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(55.dp))
        FavoriteHeader()
        Spacer(Modifier.height(20.dp))

        addToFavoriteResult.Show(
            onSuccess = {},
            onLoading = { productId ->
                ChangeProductStatus(productId, viewModel::changeStateFavoriteStatus)
            },
            onError = { productId ->
                ChangeProductStatus(productId, viewModel::changeStateFavoriteStatus)
            }
        )

        addToCartResult.Show(
            onSuccess = {},
            onLoading = { productId ->
                ChangeProductStatus(productId, viewModel::changeStateInCartStatus)
            },
            onError = { productId ->
                ChangeProductStatus(productId, viewModel::changeStateInCartStatus)
            }
        )

        catalogContent.Show(
            onSuccess = { fetchContent ->
                val favorites = fetchContent.products.filter { it.isFavorite }
                if (favorites.isEmpty()) {

                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = stringResource(R.string.no_favorite_products),
                            fontSize = 16.sp,
                            fontFamily = ralewayFamily,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 20.sp,
                            modifier = Modifier.offset(y = (-55).dp)
                        )
                    }
                } else {
                    FavoritesGrid(
                        favoriteProducts = favorites,
                        onLikeClicked = viewModel::changeFavoriteStatus,
                        onAddToCartClick = viewModel::addProductToCart,
                        onCardClick = onNavigateToDetails,
                        onNavigateToCart = onNavigateToCart
                    )
                }
            },

            onError = {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = stringResource(R.string.load_error))
                        Button(onClick = viewModel::fetchContent) {
                            Text(stringResource(R.string.retry), color = Color.White)
                        }
                    }
                }
            },

            onLoading = {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.offset(y = (-55).dp))
                }
            }
        )
    }
}


@Preview
@Composable
private fun FavoriteScreenPreview() {
    MatuleTheme {
        FavoriteScreen({}, {})
    }
}
