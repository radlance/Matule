package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val OrdersIcon: ImageVector
    get() {
        if (_OrdersIcon != null) {
            return _OrdersIcon!!
        }
        _OrdersIcon = ImageVector.Builder(
            name = "Group 1",
            defaultWidth = 29.5.dp,
            defaultHeight = 26.5.dp,
            viewportWidth = 29.5f,
            viewportHeight = 26.5f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(21.45f, 17.28f)
                curveTo(20.83f, 17.9f, 19.83f, 17.9f, 19.21f, 17.28f)
                curveTo(18.59f, 16.66f, 18.59f, 15.66f, 19.21f, 15.04f)
                curveTo(19.83f, 14.42f, 20.83f, 14.42f, 21.45f, 15.04f)
                curveTo(22.07f, 15.66f, 22.07f, 16.66f, 21.45f, 17.28f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(10.45f, 17.28f)
                curveTo(9.83f, 17.9f, 8.83f, 17.9f, 8.21f, 17.28f)
                curveTo(7.59f, 16.66f, 7.59f, 15.66f, 8.21f, 15.04f)
                curveTo(8.83f, 14.42f, 9.83f, 14.42f, 10.45f, 15.04f)
                curveTo(11.07f, 15.66f, 11.07f, 16.66f, 10.45f, 17.28f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12.75f, 0.75f)
                lineTo(16.75f, 0.75f)
                curveTo(17.3f, 0.75f, 17.75f, 1.19f, 17.75f, 1.75f)
                lineTo(17.75f, 11.75f)
                lineTo(4.75f, 11.75f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(7.75f, 16.16f)
                lineTo(5.75f, 16.16f)
                curveTo(5.19f, 16.16f, 4.75f, 15.71f, 4.75f, 15.16f)
                lineTo(4.75f, 9.75f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(17.75f, 3.75f)
                lineTo(22.07f, 3.75f)
                curveTo(22.48f, 3.75f, 22.85f, 3.99f, 23f, 4.37f)
                lineTo(24.6f, 8.39f)
                curveTo(24.7f, 8.62f, 24.75f, 8.88f, 24.75f, 9.13f)
                lineTo(24.75f, 15.08f)
                curveTo(24.75f, 15.63f, 24.3f, 16.08f, 23.75f, 16.08f)
                lineTo(21.91f, 16.08f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(18.75f, 16.17f)
                lineTo(10.91f, 16.17f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(24.75f, 10.75f)
                lineTo(20.75f, 10.75f)
                lineTo(20.75f, 6.75f)
                lineTo(23.95f, 6.75f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(4.75f, 0.75f)
                lineTo(9.75f, 0.75f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(4.75f, 3.75f)
                lineTo(7.75f, 3.75f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(5.75f, 6.75f)
                lineTo(4.75f, 6.75f)
            }
        }.build()

        return _OrdersIcon!!
    }

@Suppress("ObjectPropertyName")
private var _OrdersIcon: ImageVector? = null
