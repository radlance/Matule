package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun BagIcon(color: Color): ImageVector {

    if (_BagIcon != null) {
        return _BagIcon!!
    }
    _BagIcon = ImageVector.Builder(
        name = "Bag2",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(color),
            strokeLineWidth = 1.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(7.5f, 7.66f)
            lineTo(7.5f, 6.7f)
            curveTo(7.5f, 4.45f, 9.31f, 2.23f, 11.56f, 2.03f)
            curveTo(14.24f, 1.77f, 16.5f, 3.88f, 16.5f, 6.51f)
            lineTo(16.5f, 7.89f)
        }
        path(
            stroke = SolidColor(color),
            strokeLineWidth = 1.5f,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(15f, 22f)
            curveTo(19.02f, 22f, 19.74f, 20.39f, 19.95f, 18.43f)
            lineTo(20.7f, 12.43f)
            curveTo(20.97f, 9.98f, 20.27f, 8f, 16f, 8f)
            lineTo(8f, 8f)
            curveTo(3.72f, 8f, 3.03f, 9.98f, 3.3f, 12.43f)
            lineTo(4.05f, 18.43f)
            curveTo(4.26f, 20.39f, 4.97f, 22f, 9f, 22f)
            lineTo(15f, 22f)
            close()
        }
        path(
            stroke = SolidColor(color),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(15.49f, 12f)
            lineTo(15.5f, 12f)
        }
        path(
            stroke = SolidColor(color),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(8.49f, 12f)
            lineTo(8.5f, 12f)
        }
        path(
            fill = SolidColor(color),
            fillAlpha = 0f,
            strokeAlpha = 0f,
            pathFillType = PathFillType.EvenOdd
        ) {
            moveTo(0f, 0f)
            lineTo(24f, 0f)
            lineTo(24f, 24f)
            lineTo(0f, 24f)
            lineTo(0f, 0f)
            close()
            moveTo(1f, 23f)
            lineTo(1f, 1f)
            lineTo(23f, 1f)
            lineTo(23f, 23f)
            lineTo(1f, 23f)
            close()
        }
    }.build()

    return _BagIcon!!

}

@Suppress("ObjectPropertyName")
private var _BagIcon: ImageVector? = null
