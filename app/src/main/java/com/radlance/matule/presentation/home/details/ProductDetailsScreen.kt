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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.home.HomeViewModel
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun ProductDetailsScreen(
    selectedProductId: Int,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val catalogContent by viewModel.catalogContent.collectAsState()


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
                val selectedProduct = fetchContent.products.first { it.id == selectedProductId }
                ProductDetails(selectedProduct = selectedProduct)
                Spacer(Modifier.height(10.dp))

                DetailsAdditionalRow(otherProducts = fetchContent.products)
                Spacer(Modifier.height(33.dp))

                ProductDetailsDescription(
                    selectedProduct = selectedProduct,
                    modifier = Modifier.align(Alignment.End)
                )
                Spacer(Modifier.weight(1f))

                ProductDetailsBottomContent(
                    isFavorite = selectedProduct.isFavorite,
                    inCart = selectedProduct.inCart
                )
            },
            onError = {},
            onLoading = {
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        )
    }
}


@Preview
@Composable
private fun ProductDetailsScreenPreview() {
    MatuleTheme {
        ProductDetailsScreen(selectedProductId = 1, onBackPressed = {})
    }
}