package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun NotificationNavigationIcon(color: Color): ImageVector {
    return ImageVector.Builder(
        name = "NotificationIcon",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        group {
            path(
                stroke = SolidColor(color),
                strokeLineWidth = 1.7f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(6.22f, 18f)
                curveTo(4.39f, 18f, 3.35f, 16.16f, 4.44f, 14.87f)
                curveTo(4.93f, 14.3f, 5.23f, 13.62f, 5.31f, 12.91f)
                lineTo(5.76f, 8.97f)
                curveTo(6f, 6.85f, 7.58f, 5.1f, 9.72f, 4.37f)
                lineTo(9.72f, 4.26f)
                curveTo(9.72f, 3.01f, 10.74f, 2f, 12f, 2f)
                curveTo(13.25f, 2f, 14.27f, 3.01f, 14.27f, 4.26f)
                lineTo(14.27f, 4.37f)
                curveTo(16.41f, 5.1f, 17.99f, 6.85f, 18.23f, 8.97f)
                lineTo(18.68f, 12.91f)
                curveTo(18.76f, 13.62f, 19.06f, 14.3f, 19.55f, 14.87f)
                curveTo(20.64f, 16.16f, 19.6f, 18f, 17.77f, 18f)
                lineTo(6.22f, 18f)
                close()
                moveTo(15f, 20f)
                curveTo(14.56f, 21.16f, 13.38f, 22f, 12f, 22f)
                curveTo(10.61f, 22f, 9.43f, 21.16f, 9f, 20f)
            }
        }
    }.build()
}