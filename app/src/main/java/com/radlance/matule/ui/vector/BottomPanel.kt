package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun BottomPanel(color: Color): ImageVector {
    return ImageVector.Builder(
        name = "Vector 1789",
        defaultWidth = 405.dp,
        defaultHeight = 136.dp,
        viewportWidth = 405f,
        viewportHeight = 136f
    ).apply {
        path(
            fill = SolidColor(color),
            pathFillType = PathFillType.EvenOdd
        ) {
            moveTo(15f, 11f)
            curveTo(32.69f, 18.33f, 81.78f, 32.7f, 136.66f, 31.5f)
            curveTo(161.19f, 31.5f, 158.69f, 42.5f, 159.69f, 55.5f)
            curveTo(160.53f, 66.5f, 170.7f, 84f, 222.77f, 77.5f)
            curveTo(249.38f, 74.17f, 244.8f, 51f, 244.8f, 42.5f)
            curveTo(244.8f, 34f, 248.81f, 31f, 270.34f, 31f)
            curveTo(291.86f, 31f, 362.96f, 30.5f, 390f, 11f)
            lineTo(390f, 117f)
            lineTo(15f, 117f)
            lineTo(15f, 11f)
            close()
        }
    }.build()
}



