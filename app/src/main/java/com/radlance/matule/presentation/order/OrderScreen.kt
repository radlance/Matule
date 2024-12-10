package com.radlance.matule.presentation.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(dimensionResource(R.dimen.main_top_padding)))
        OrderHeader(onBackPressed = onBackPressed)
        Spacer(Modifier.height(46.dp))
        OrderCard(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .weight(4f)
        )
        Box(
            modifier = Modifier.weight(3f)
        ) {
            CartResult(
                productsPrice = 753.95,
                deliveryPrice = 60.20,
                buttonStringResId = R.string.confirm,
                onButtonClick = {}
            )
        }
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