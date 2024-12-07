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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.home.HomeViewModel
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val catalogContent by viewModel.catalogContent.collectAsState()


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(55.dp))
        CartHeader()
        Spacer(Modifier.height(16.dp))

        catalogContent.Show(
            onSuccess = { fetchContent ->
                val productsInCart = fetchContent.products.filter { product ->
                    product.quantityInCart != 0
                }
                CartProductColumn(
                    productsInCart,
                    modifier = Modifier.weight(4f)
                )
                Box(
                    modifier = Modifier.weight(3f)
                ) {
                    CartResult(
                        productsPrice = productsInCart.sumOf { it.price * it.quantityInCart },
                        deliveryPrice = 60.20,
                        onPlaceOrderClick = {}
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
private fun CartScreenPreview() {
    MatuleTheme {
        CartScreen()
    }
}