package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun FavoriteIcon(
    fillColor: Color,
    width: Dp = 13.2.dp,
    height: Dp = 11.867.dp
): ImageVector {
    return ImageVector.Builder(
        name = "Path",
        defaultWidth = width,
        defaultHeight = height,
        viewportWidth = 13.2f,
        viewportHeight = 11.867f
    ).apply {
        path(
            fill = SolidColor(fillColor),
            pathFillType = PathFillType.EvenOdd
        ) {
            moveTo(9.06f, 0.59f)
            curveTo(11.18f, 0.59f, 12.6f, 2.58f, 12.6f, 4.43f)
            curveTo(12.6f, 8.19f, 6.7f, 11.26f, 6.59f, 11.26f)
            curveTo(6.49f, 11.26f, 0.59f, 8.19f, 0.59f, 4.43f)
            curveTo(0.59f, 2.58f, 2.01f, 0.59f, 4.13f, 0.59f)
            curveTo(5.34f, 0.59f, 6.14f, 1.2f, 6.59f, 1.74f)
            curveTo(7.05f, 1.2f, 7.85f, 0.59f, 9.06f, 0.59f)
            close()
        }
        path(
            stroke = SolidColor(fillColor),
            strokeLineWidth = 1.2f,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(12.6f, 4.43f)
            curveTo(12.6f, 8.19f, 6.7f, 11.26f, 6.59f, 11.26f)
            curveTo(6.49f, 11.26f, 0.59f, 8.19f, 0.59f, 4.43f)
            curveTo(0.59f, 2.58f, 2.01f, 0.59f, 4.13f, 0.59f)
            curveTo(5.34f, 0.59f, 6.14f, 1.2f, 6.59f, 1.74f)
            curveTo(7.05f, 1.2f, 7.85f, 0.59f, 9.06f, 0.59f)
            curveTo(11.18f, 0.59f, 12.6f, 2.58f, 12.6f, 4.43f)
            close()
        }
    }.build()
}



