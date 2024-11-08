package com.radlance.matule.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.radlance.matule.ui.theme.ralewayFamily
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onDelayFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        delay(1000)
        onDelayFinished()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(color = 0xff48b2e7),
                        Color(color = 0xff0176b1)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontFamily = ralewayFamily, fontWeight = FontWeight.Bold)) {
                append("MATULE")
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 36.sp,
                    baselineShift = BaselineShift(0.4f),
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Light
                ),
            ) {
                append(" ME")
            }
        }

        Text(
            text = text,
            fontSize = 65.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(onDelayFinished = {})
}