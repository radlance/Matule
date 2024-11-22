package com.radlance.matule.presentation.home.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun MenuIcon(mainColor: Color): ImageVector {
    return ImageVector.Builder(
        name = "HomeHighlight1",
        defaultWidth = 25.714.dp,
        defaultHeight = 18.dp,
        viewportWidth = 25.714f,
        viewportHeight = 18f
    ).apply {
        group {
            path(fill = SolidColor(mainColor)) {
                moveTo(10f, 1f)
                lineTo(24f, 1f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 25f, 2f)
                lineTo(25f, 2f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 24f, 3f)
                lineTo(10f, 3f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9f, 2f)
                lineTo(9f, 2f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10f, 1f)
                close()
            }
            path(fill = SolidColor(mainColor)) {
                moveTo(3f, 8f)
                lineTo(24f, 8f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 25f, 9f)
                lineTo(25f, 9f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 24f, 10f)
                lineTo(3f, 10f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 9f)
                lineTo(2f, 9f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3f, 8f)
                close()
            }
            path(fill = SolidColor(Color(0xFF48B2E7))) {
                moveTo(3f, 15f)
                lineTo(12f, 15f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 13f, 16f)
                lineTo(13f, 16f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 17f)
                lineTo(3f, 17f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 16f)
                lineTo(2f, 16f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3f, 15f)
                close()
            }
        }
    }.build()
}