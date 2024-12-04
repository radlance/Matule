package com.radlance.matule.presentation.home.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ChangeProductStatus(
    productId: Int?,
    onStatusChanged: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        productId?.let { onStatusChanged(productId) }
    }
}
