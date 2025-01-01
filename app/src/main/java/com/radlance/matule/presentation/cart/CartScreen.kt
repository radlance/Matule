package com.radlance.matule.presentation.cart

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.radlance.matule.presentation.common.ProductViewModel
import com.radlance.matule.presentation.home.common.ChangeProductStatus
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun CartScreen(
    onPlaceOrderClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val catalogContent by viewModel.catalogContent.collectAsState()
    val quantityResult by viewModel.quantityResult.collectAsState()
    val removeResult by viewModel.removeResult.collectAsState()

    var incrementCurrent by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(55.dp))
        CartHeader()
        Spacer(Modifier.height(16.dp))

        removeResult.Show(
            onSuccess = {},
            onError = { productId ->
                ChangeProductStatus(
                    productId = productId,
                    onStatusChanged = {
                        viewModel.deleteCartItemFromCurrentState(
                            productId = it,
                            recover = true
                        )
                    }
                )

            },
            onLoading = { productId ->
                ChangeProductStatus(
                    productId = productId,
                    onStatusChanged = viewModel::deleteCartItemFromCurrentState
                )
            },
            onUnauthorized = {}
        )

        quantityResult.Show(
            onSuccess = {},
            onError = { productId ->
                ChangeProductStatus(productId) {
                    viewModel.updateCurrentQuantity(it, !incrementCurrent)
                }
            },
            onLoading = { productId ->
                ChangeProductStatus(productId) {
                    viewModel.updateCurrentQuantity(it, incrementCurrent)
                }
            },
            onUnauthorized = {}
        )

        catalogContent.Show(
            onSuccess = { fetchContent ->
                val productsInCart = fetchContent.products.filter { product ->
                    product.quantityInCart != 0
                }
                if (productsInCart.isEmpty()) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = stringResource(R.string.cart_is_empty),
                            fontSize = 16.sp,
                            fontFamily = ralewayFamily,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 20.sp,
                            modifier = Modifier.offset(y = (-55).dp)
                        )
                    }
                } else {
                    CartProductColumn(
                        products = productsInCart,
                        onChangeQuantityClick = { productId, quantity, increment ->
                            viewModel.updateProductQuantity(productId, quantity)
                            incrementCurrent = increment
                        },

                        onRemoveProduct = viewModel::removeProductFromCart,

                        modifier = Modifier.weight(4f)
                    )
                    Box(
                        modifier = Modifier.weight(3f)
                    ) {
                        CartResult(
                            productsPrice = productsInCart.sumOf { it.price * it.quantityInCart },
                            deliveryPrice = 60.20,
                            buttonStringResId = R.string.place_order,
                            onButtonClick = onPlaceOrderClick
                        )
                    }
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
            },
            onUnauthorized = {}
        )


    }
}

@Preview
@Composable
private fun CartScreenPreview() {
    MatuleTheme {
        CartScreen({})
    }
}