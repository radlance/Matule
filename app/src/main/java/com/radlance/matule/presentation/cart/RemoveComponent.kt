package com.radlance.matule.presentation.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.fillRedColor

@Composable
fun RemoveComponent(
    onRemoveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .height(104.dp)
            .width(58.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(fillRedColor)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_trash),
            contentDescription = "ic_trash",
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onRemoveClick() }
        )
    }
}

@Preview
@Composable
private fun RemoveComponentPreview() {
    MatuleTheme {
        RemoveComponent(onRemoveClick = {})
    }
}