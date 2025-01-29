package com.radlance.matule.presentation.home.catalog

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.poppinsFamily

@Composable
fun CategoryItem(
    categoryTitle: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val boxBackgroundColor by animateColorAsState(
        if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }
    )

    val textColor by animateColorAsState(
        if (selected) {
            Color.White
        } else {
            MaterialTheme.colorScheme.onSurface
        }
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(108.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(boxBackgroundColor)
    ) {
        Text(
            text = categoryTitle,
            color = textColor,
            fontSize = 12.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            lineHeight = 18.sp
        )
    }
}

@Preview
@Composable
private fun CategoryItemUnselectedPreview() {
    MatuleTheme {
        CategoryItem(categoryTitle = "Tennis", selected = false)
    }
}

@Preview
@Composable
private fun CategoryItemSelectedPreview() {
    MatuleTheme {
        CategoryItem(categoryTitle = "Tennis", selected = true)
    }
}