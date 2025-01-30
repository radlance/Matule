package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun FavoriteOutlinedIcon(
    color: Color,
    width: Dp = 24.dp,
    height: Dp = 24.dp,
): ImageVector {
    return ImageVector.Builder(
        name = "HeartIcon 1",
        defaultWidth = width,
        defaultHeight = height,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            fill = SolidColor(color),
            fillAlpha = 0f
        ) {
            moveTo(0.5f, 0.5f)
            horizontalLineToRelative(23f)
            verticalLineToRelative(23f)
            horizontalLineToRelative(-23f)
            close()
        }
        path(
            stroke = SolidColor(color),
            strokeLineWidth = 1.7f,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(21f, 9.75f)
            curveTo(21f, 15.38f, 12.16f, 20f, 12f, 20f)
            curveTo(11.83f, 20f, 3f, 15.38f, 3f, 9.75f)
            curveTo(3f, 6.97f, 5.12f, 4f, 8.3f, 4f)
            curveTo(10.11f, 4f, 11.31f, 4.9f, 12f, 5.71f)
            curveTo(12.68f, 4.9f, 13.88f, 4f, 15.69f, 4f)
            curveTo(18.87f, 4f, 21f, 6.97f, 21f, 9.75f)
            close()
        }
    }.build()
}
