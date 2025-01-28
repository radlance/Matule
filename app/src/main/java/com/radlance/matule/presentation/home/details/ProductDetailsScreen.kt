package com.radlance.matule.presentation.home.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.common.ProductViewModel
import com.radlance.matule.presentation.home.common.ChangeProductStatus
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun ProductDetailsScreen(
    selectedProductId: Int,
    onBackPressed: () -> Unit,
    onNavigateToCart: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val catalogContent by viewModel.catalogContent.collectAsState()

    val addToFavoriteResult by viewModel.favoriteResult.collectAsState()
    val addToCartResult by viewModel.inCartResult.collectAsState()

    addToFavoriteResult.Show(
        onSuccess = {},
        onLoading = { productId ->
            ChangeProductStatus(productId, viewModel::changeStateFavoriteStatus)
        },
        onError = { productId ->
            ChangeProductStatus(productId, viewModel::changeStateFavoriteStatus)
        },
        onUnauthorized = {},
    )

    addToCartResult.Show(
        onSuccess = {},
        onLoading = { productId ->
            ChangeProductStatus(productId, viewModel::changeStateInCartStatus)
        },
        onError = { productId ->
            ChangeProductStatus(
                productId = productId,
                onStatusChanged = { viewModel.changeStateInCartStatus(it, recover = true) }
            )
        },
        onUnauthorized = {}
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(dimensionResource(R.dimen.main_top_padding)))
        DetailsHeader(onBackPressed = onBackPressed)

        Spacer(Modifier.height(26.dp))

        catalogContent.Show(
            onSuccess = { fetchContent ->

                var currentProductId by rememberSaveable { mutableIntStateOf(selectedProductId) }
                val selectedProduct by viewModel.selectedProduct.collectAsState()

                viewModel.selectProduct(fetchContent.products.first { it.id == currentProductId })

                selectedProduct?.let { product ->
                    ProductDetails(selectedProduct = product)
                    Spacer(Modifier.height(10.dp))

                    DetailsAdditionalRow(
                        otherProducts = fetchContent.products,
                        selectedProductId = product.id,
                        onItemClick = { currentProductId = it.id }
                    )
                    Spacer(Modifier.height(33.dp))

                    ProductDetailsDescription(
                        selectedProduct = product,
                        modifier = Modifier.align(Alignment.End)
                    )

                    Spacer(Modifier.height(60.dp))

                    ProductDetailsBottomContent(
                        isFavorite = product.isFavorite,
                        inCart = product.quantityInCart != 0,
                        onLikeClick = {
                            viewModel.changeFavoriteStatus(product.id)
                        },
                        onCartClick = { viewModel.addProductToCart(product.id) },
                        onNavigateToCart = onNavigateToCart
                    )
                }
            },
            onError = {},
            onLoading = {
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            },
            onUnauthorized = {}
        )
    }
}


@Preview
@Composable
private fun ProductDetailsScreenPreview() {
    MatuleTheme {
        ProductDetailsScreen(selectedProductId = 1, onBackPressed = {}, onNavigateToCart = {})
    }
}