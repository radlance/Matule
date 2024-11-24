package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SearchSettingsIcon: ImageVector
    get() {
        if (_SearchSettingsIcon != null) {
            return _SearchSettingsIcon!!
        }
        _SearchSettingsIcon = ImageVector.Builder(
            name = "Sliders",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 0f
            ) {
                moveTo(0.5f, 24.5f)
                lineToRelative(0f, -23f)
                lineToRelative(23f, -0f)
                lineToRelative(0f, 23f)
                close()
            }
            group {
                path(
                    stroke = SolidColor(Color(0xFFF7F7F9)),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(21f, 20f)
                    lineTo(14f, 20f)
                }
                path(
                    stroke = SolidColor(Color(0xFFF7F7F9)),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(10f, 20f)
                    lineTo(3f, 20f)
                }
                path(
                    stroke = SolidColor(Color(0xFFF7F7F9)),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(21f, 12f)
                    lineTo(12f, 12f)
                }
                path(
                    stroke = SolidColor(Color(0xFFF7F7F9)),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(8f, 12f)
                    lineTo(3f, 12f)
                }
                path(
                    stroke = SolidColor(Color(0xFFF7F7F9)),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(21f, 4f)
                    lineTo(16f, 4f)
                }
                path(
                    stroke = SolidColor(Color(0xFFF7F7F9)),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(12f, 4f)
                    lineTo(3f, 4f)
                }
                path(
                    stroke = SolidColor(Color(0xFFF7F7F9)),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(14f, 23f)
                    lineTo(14f, 17f)
                }
                path(
                    stroke = SolidColor(Color(0xFFF7F7F9)),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(8f, 15f)
                    lineTo(8f, 9f)
                }
                path(
                    stroke = SolidColor(Color(0xFFF7F7F9)),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(16f, 7f)
                    lineTo(16f, 1f)
                }
            }
        }.build()

        return _SearchSettingsIcon!!
    }

@Suppress("ObjectPropertyName")
private var _SearchSettingsIcon: ImageVector? = null
