package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ExitIcon: ImageVector
    get() {
        if (_ExitIcon != null) {
            return _ExitIcon!!
        }
        _ExitIcon = ImageVector.Builder(
            name = "Vector 7",
            defaultWidth = 19.5.dp,
            defaultHeight = 19.5.dp,
            viewportWidth = 19.5f,
            viewportHeight = 19.5f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15.75f, 6.75f)
                lineTo(18.75f, 9.75f)
                lineTo(15.75f, 12.75f)
                moveTo(18.75f, 9.75f)
                lineTo(6.75f, 9.75f)
                moveTo(12.75f, 5.25f)
                lineTo(12.75f, 4.75f)
                curveTo(12.75f, 2.54f, 10.95f, 0.75f, 8.75f, 0.75f)
                lineTo(4.75f, 0.75f)
                curveTo(2.54f, 0.75f, 0.75f, 2.54f, 0.75f, 4.75f)
                lineTo(0.75f, 14.75f)
                curveTo(0.75f, 16.95f, 2.54f, 18.75f, 4.75f, 18.75f)
                lineTo(8.75f, 18.75f)
                curveTo(10.95f, 18.75f, 12.75f, 16.95f, 12.75f, 14.75f)
                lineTo(12.75f, 14.25f)
            }
        }.build()

        return _ExitIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ExitIcon: ImageVector? = null
