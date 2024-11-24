package com.radlance.matule.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.vector.CartIcon

@Composable
fun PopularRow(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 11.5.dp, end = 28.5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Популярное",
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp
            )

            Text(
                text = "Все",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 20.dp)
        ) {
            ShoesCard(
                onLikeClick = {},
                isFavorite = true,
                icon = Icons.Filled.Add,
                modifier = modifier.weight(1f)
            )

            Spacer(Modifier.width(19.dp))

            ShoesCard(
                onLikeClick = {},
                isFavorite = true,
                icon = CartIcon,
                modifier = modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PopularRowPreview() {
    MatuleTheme {
        PopularRow()
    }
}