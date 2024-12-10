package com.radlance.matule.presentation.cart

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.NavigationButton
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.theme.secondaryTextColor
import kotlin.math.roundToInt
import kotlin.math.roundToLong

@Composable
fun CartResult(
    productsPrice: Double,
    deliveryPrice: Double,
    @StringRes buttonStringResId: Int,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState).padding(horizontal = 20.dp)) {
            Spacer(Modifier.height(34.dp))
            ResultSection(titleResId = R.string.sum, price = productsPrice)
            Spacer(Modifier.height(8.dp))
            ResultSection(titleResId = R.string.delivery, price = deliveryPrice)
            Spacer(Modifier.height(15.dp))
            Box(
                Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(secondaryTextColor, shape = DottedShape(step = 10.dp))
            )

            Spacer(Modifier.height(17.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.result),
                    fontSize = 16.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp
                )

                Text(
                    text = "₽${((productsPrice + deliveryPrice) * 100).roundToLong() / 100.0}",
                    fontFamily = poppinsFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(Modifier.height(30.dp))

            NavigationButton(
                buttonStringResId,
                onClick = onButtonClick,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(120.dp))
        }
    }
}

private data class DottedShape(
    val step: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(Path().apply {
        val stepPx = with(density) { step.toPx() }
        val stepsCount = (size.width / stepPx).roundToInt()
        val actualStep = size.width / stepsCount
        val dotSize = Size(width = actualStep / 2, height = size.height)
        for (i in 0 until stepsCount) {
            addRect(
                Rect(
                    offset = Offset(x = i * actualStep, y = 0f),
                    size = dotSize
                )
            )
        }
        close()
    })
}

@Composable
private fun ResultSection(
    @StringRes titleResId: Int,
    price: Double
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(titleResId),
            fontSize = 16.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Medium,
            color = secondaryTextColor,
            lineHeight = 24.sp
        )

        Text(
            text = "₽${price}",
            fontFamily = poppinsFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp
        )
    }
}

@Preview
@Composable
private fun CartResultPreview() {
    MatuleTheme {
        CartResult(productsPrice = 753.95, deliveryPrice = 60.20, onButtonClick = {}, buttonStringResId = R.string.place_order)
    }
}