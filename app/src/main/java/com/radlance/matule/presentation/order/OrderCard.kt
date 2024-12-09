package com.radlance.matule.presentation.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun OrderCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(16.dp))
            ContactIInformation(
                email = "emmanueloyiboke@gmail.com",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@Preview
@Composable
private fun OrderCardPreview() {
    MatuleTheme {
        OrderCard(modifier = Modifier
            .height(425.dp)
            .width(347.dp))
    }
}