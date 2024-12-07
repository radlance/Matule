package com.radlance.matule.presentation.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun ChangeQuantityComponent(
    quantity: Int,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .height(104.dp)
            .width(58.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Spacer(Modifier.height(14.dp))
        Image(
            painter = painterResource(R.drawable.ic_increment),
            contentDescription = "ic_increment",
            modifier = Modifier.clickable { onIncrementClick() }
        )

        Text(
            text = quantity.toString(),
            fontSize = 14.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )

        Image(
            painter = painterResource(R.drawable.ic_decrement),
            contentDescription = "ic_decrement",
            modifier = Modifier.clickable { onDecrementClick() }
        )
        Spacer(Modifier.height(14.dp))

    }
}

@Preview
@Composable
private fun ChangeQuantityComponentPreview() {
    MatuleTheme {
        ChangeQuantityComponent(quantity = 3, onIncrementClick = {}, onDecrementClick = {})
    }
}