package com.radlance.matule.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.vector.CartIcon

@Composable
fun PriceRow(
    price: String,
    inCart: Boolean,
    onCartClicked: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(34.dp)
            .padding(start = 9.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "₽$price",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp
        )

        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .size(35.dp)
                .clip(RoundedCornerShape(topStart = 17.dp, bottomEnd = 17.dp))
                .background(MaterialTheme.colorScheme.primary)
        ) {
            val icon =if(inCart) {
                CartIcon
            } else {
                Icons.Filled.Add
            }

            IconButton(onClick = onCartClicked) {
                Icon(icon, contentDescription = contentDescription, tint = Color.White)
            }
        }
    }
}