package com.radlance.matule.presentation.order

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.cart.CartResult
import com.radlance.matule.presentation.home.HomeViewModel
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun OrderScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val catalogContent by viewModel.catalogContent.collectAsState()
    val placeOrderResult by viewModel.placeOrderResult.collectAsState()

    val context = LocalContext.current

    var placeOrderButtonEnabled by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(dimensionResource(R.dimen.main_top_padding)))
        OrderHeader(onBackPressed = onBackPressed)
        Spacer(Modifier.height(46.dp))

        catalogContent.Show(
            onSuccess = { fetchContent ->
                val productsInCart = fetchContent.products.filter { product ->
                    product.quantityInCart != 0
                }

                OrderCard(
                    modifier = Modifier
                        .padding(horizontal = 14.dp)
                        .weight(4f)
                )
                Box(
                    modifier = Modifier.weight(3f)
                ) {
                    CartResult(
                        productsPrice = productsInCart.sumOf { it.price * it.quantityInCart },
                        deliveryPrice = 60.20,
                        buttonStringResId = R.string.confirm,
                        buttonEnabled = placeOrderButtonEnabled,
                        onButtonClick = { viewModel.placeOrder(productsInCart) }
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

        placeOrderResult.Show(
            onSuccess = {
                placeOrderButtonEnabled = true
                Toast.makeText(
                    context,
                    stringResource(R.string.place_order_success),
                    Toast.LENGTH_LONG
                ).show()
            },
            onLoading = { placeOrderButtonEnabled = false },
            onError = {
                placeOrderButtonEnabled = true
                Toast.makeText(
                    context,
                    stringResource(R.string.place_order_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
            )
        }
}

@Preview
@Composable
private fun OrderScreenPreview() {
    MatuleTheme {
        OrderScreen(onBackPressed = {})
    }
}

@Preview(device = "id:Nexus 5X")
@Composable
private fun OrderScreenSmallPreview() {
    MatuleTheme {
        OrderScreen(onBackPressed = {})
    }
}
