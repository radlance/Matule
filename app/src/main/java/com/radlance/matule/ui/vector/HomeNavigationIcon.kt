package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun HomeNavigationIcon(color: Color): ImageVector {
    return ImageVector.Builder(
        name = "VuesaxOutlineHome2",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            fill = SolidColor(color),
            fillAlpha = 0f
        ) {
            moveTo(1.5f, 0.5f)
            horizontalLineToRelative(21f)
            verticalLineToRelative(23f)
            horizontalLineToRelative(-21f)
            close()
        }
        path(fill = SolidColor(color)) {
            moveTo(17.79f, 22.75f)
            lineTo(6.2f, 22.75f)
            curveTo(3.46f, 22.75f, 1.25f, 20.52f, 1.25f, 17.78f)
            lineTo(1.25f, 10.37f)
            curveTo(1.25f, 9.01f, 2.09f, 7.29f, 3.16f, 6.45f)
            lineTo(8.56f, 2.26f)
            curveTo(10.17f, 1f, 12.77f, 0.93f, 14.45f, 2.11f)
            lineTo(20.62f, 6.44f)
            curveTo(21.82f, 7.28f, 22.75f, 9.05f, 22.75f, 10.51f)
            lineTo(22.75f, 17.78f)
            curveTo(22.75f, 20.52f, 20.53f, 22.75f, 17.79f, 22.75f)
            close()
            moveTo(9.47f, 3.43f)
            lineTo(4.09f, 7.64f)
            curveTo(3.37f, 8.19f, 2.75f, 9.46f, 2.75f, 10.37f)
            lineTo(2.75f, 17.78f)
            curveTo(2.75f, 19.68f, 4.3f, 21.25f, 6.2f, 21.25f)
            lineTo(17.79f, 21.25f)
            curveTo(19.7f, 21.25f, 21.25f, 19.7f, 21.25f, 17.78f)
            lineTo(21.25f, 10.51f)
            curveTo(21.25f, 9.54f, 20.56f, 8.21f, 19.77f, 7.67f)
            lineTo(13.59f, 3.34f)
            curveTo(12.45f, 2.54f, 10.57f, 2.58f, 9.47f, 3.43f)
            close()
        }
        path(
            fill = SolidColor(color),
            pathFillType = PathFillType.EvenOdd
        ) {
            moveTo(17.79f, 22.75f)
            lineTo(6.2f, 22.75f)
            curveTo(3.46f, 22.75f, 1.25f, 20.52f, 1.25f, 17.78f)
            lineTo(1.25f, 10.37f)
            curveTo(1.25f, 9.01f, 2.09f, 7.29f, 3.16f, 6.45f)
            lineTo(8.56f, 2.26f)
            curveTo(10.17f, 1f, 12.77f, 0.93f, 14.45f, 2.11f)
            lineTo(20.62f, 6.44f)
            curveTo(21.82f, 7.28f, 22.75f, 9.05f, 22.75f, 10.51f)
            lineTo(22.75f, 17.78f)
            curveTo(22.75f, 20.52f, 20.53f, 22.75f, 17.79f, 22.75f)
            close()
            moveTo(9.47f, 3.43f)
            lineTo(4.09f, 7.64f)
            curveTo(3.37f, 8.19f, 2.75f, 9.46f, 2.75f, 10.37f)
            lineTo(2.75f, 17.78f)
            curveTo(2.75f, 19.68f, 4.3f, 21.25f, 6.2f, 21.25f)
            lineTo(17.79f, 21.25f)
            curveTo(19.7f, 21.25f, 21.25f, 19.7f, 21.25f, 17.78f)
            lineTo(21.25f, 10.51f)
            curveTo(21.25f, 9.54f, 20.56f, 8.21f, 19.77f, 7.67f)
            lineTo(13.59f, 3.34f)
            curveTo(12.45f, 2.54f, 10.57f, 2.58f, 9.47f, 3.43f)
            close()
        }
        path(fill = SolidColor(color)) {
            moveTo(12f, 18.75f)
            curveTo(11.59f, 18.75f, 11.25f, 18.41f, 11.25f, 18f)
            lineTo(11.25f, 15f)
            curveTo(11.25f, 14.59f, 11.59f, 14.25f, 12f, 14.25f)
            curveTo(12.4f, 14.25f, 12.75f, 14.59f, 12.75f, 15f)
            lineTo(12.75f, 18f)
            curveTo(12.75f, 18.41f, 12.4f, 18.75f, 12f, 18.75f)
            close()
        }
        path(
            fill = SolidColor(color),
            pathFillType = PathFillType.EvenOdd
        ) {
            moveTo(11.25f, 18f)
            curveTo(11.25f, 18.41f, 11.59f, 18.75f, 12f, 18.75f)
            curveTo(12.4f, 18.75f, 12.75f, 18.41f, 12.75f, 18f)
            lineTo(12.75f, 15f)
            curveTo(12.75f, 14.59f, 12.4f, 14.25f, 12f, 14.25f)
            curveTo(11.59f, 14.25f, 11.25f, 14.59f, 11.25f, 15f)
            lineTo(11.25f, 18f)
            close()
        }
    }.build()


}
