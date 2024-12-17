package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SettingsIcon: ImageVector
    get() {
        if (_SettingsIcon != null) {
            return _SettingsIcon!!
        }
        _SettingsIcon = ImageVector.Builder(
            name = "Vector 6",
            defaultWidth = 19.5.dp,
            defaultHeight = 19.5.dp,
            viewportWidth = 19.5f,
            viewportHeight = 19.5f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(10.7f, 0.75f)
                curveTo(11.75f, 0.75f, 12.61f, 1.55f, 12.61f, 2.55f)
                curveTo(12.61f, 3.68f, 13.83f, 4.41f, 14.83f, 3.86f)
                lineTo(14.93f, 3.81f)
                curveTo(15.84f, 3.31f, 17.01f, 3.6f, 17.53f, 4.47f)
                curveTo(18.06f, 5.33f, 18.49f, 6.02f, 18.49f, 6.02f)
                curveTo(19.02f, 6.89f, 18.7f, 7.99f, 17.79f, 8.48f)
                curveTo(16.79f, 9.03f, 16.79f, 10.46f, 17.79f, 11.01f)
                curveTo(18.7f, 11.5f, 19.02f, 12.6f, 18.49f, 13.47f)
                lineTo(17.53f, 15.02f)
                curveTo(17.01f, 15.89f, 15.84f, 16.18f, 14.93f, 15.68f)
                lineTo(14.83f, 15.63f)
                curveTo(13.83f, 15.08f, 12.61f, 15.81f, 12.61f, 16.95f)
                curveTo(12.61f, 17.94f, 11.75f, 18.75f, 10.7f, 18.75f)
                lineTo(8.79f, 18.75f)
                curveTo(7.74f, 18.75f, 6.88f, 17.94f, 6.88f, 16.95f)
                curveTo(6.88f, 15.81f, 5.66f, 15.08f, 4.66f, 15.63f)
                lineTo(4.56f, 15.68f)
                curveTo(3.65f, 16.18f, 2.48f, 15.89f, 1.96f, 15.02f)
                lineTo(1f, 13.47f)
                curveTo(0.47f, 12.6f, 0.79f, 11.5f, 1.7f, 11.01f)
                curveTo(2.7f, 10.46f, 2.7f, 9.03f, 1.7f, 8.48f)
                curveTo(0.79f, 7.99f, 0.47f, 6.89f, 1f, 6.02f)
                lineTo(1.96f, 4.47f)
                curveTo(2.48f, 3.6f, 3.65f, 3.31f, 4.56f, 3.81f)
                lineTo(4.66f, 3.86f)
                curveTo(5.66f, 4.41f, 6.88f, 3.68f, 6.88f, 2.55f)
                curveTo(6.88f, 1.55f, 7.74f, 0.75f, 8.79f, 0.75f)
                lineTo(10.7f, 0.75f)
                close()
                moveTo(9.75f, 12.75f)
                curveTo(8.09f, 12.75f, 6.75f, 11.4f, 6.75f, 9.75f)
                curveTo(6.75f, 8.09f, 8.09f, 6.75f, 9.75f, 6.75f)
                curveTo(11.4f, 6.75f, 12.75f, 8.09f, 12.75f, 9.75f)
                curveTo(12.75f, 11.4f, 11.4f, 12.75f, 9.75f, 12.75f)
                close()
            }
        }.build()

        return _SettingsIcon!!
    }

@Suppress("ObjectPropertyName")
private var _SettingsIcon: ImageVector? = null
