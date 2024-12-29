package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GoogleIcon: ImageVector
    get() {
        if (_GoogleIcon != null) {
            return _GoogleIcon!!
        }
        _GoogleIcon = ImageVector.Builder(
            name = "IconName",
            defaultWidth = 48.dp,
            defaultHeight = 48.dp,
            viewportWidth = 48f,
            viewportHeight = 48f
        ).apply {
            path(fill = SolidColor(Color(0xFFEA4335))) {
                moveTo(24f, 9.5f)
                curveToRelative(3.54f, 0f, 6.71f, 1.22f, 9.21f, 3.6f)
                lineToRelative(6.85f, -6.85f)
                curveTo(35.9f, 2.38f, 30.47f, 0f, 24f, 0f)
                curveTo(14.62f, 0f, 6.51f, 5.38f, 2.56f, 13.22f)
                lineToRelative(7.98f, 6.19f)
                curveTo(12.43f, 13.72f, 17.74f, 9.5f, 24f, 9.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFF4285F4))) {
                moveTo(46.98f, 24.55f)
                curveToRelative(0f, -1.57f, -0.15f, -3.09f, -0.38f, -4.55f)
                horizontalLineTo(24f)
                verticalLineToRelative(9.02f)
                horizontalLineToRelative(12.94f)
                curveToRelative(-0.58f, 2.96f, -2.26f, 5.48f, -4.78f, 7.18f)
                lineToRelative(7.73f, 6f)
                curveToRelative(4.51f, -4.18f, 7.09f, -10.36f, 7.09f, -17.65f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFBBC05))) {
                moveTo(10.53f, 28.59f)
                curveToRelative(-0.48f, -1.45f, -0.76f, -2.99f, -0.76f, -4.59f)
                reflectiveCurveToRelative(0.27f, -3.14f, 0.76f, -4.59f)
                lineToRelative(-7.98f, -6.19f)
                curveTo(0.92f, 16.46f, 0f, 20.12f, 0f, 24f)
                curveToRelative(0f, 3.88f, 0.92f, 7.54f, 2.56f, 10.78f)
                lineToRelative(7.97f, -6.19f)
                close()
            }
            path(fill = SolidColor(Color(0xFF34A853))) {
                moveTo(24f, 48f)
                curveToRelative(6.48f, 0f, 11.93f, -2.13f, 15.89f, -5.81f)
                lineToRelative(-7.73f, -6f)
                curveToRelative(-2.15f, 1.45f, -4.92f, 2.3f, -8.16f, 2.3f)
                curveToRelative(-6.26f, 0f, -11.57f, -4.22f, -13.47f, -9.91f)
                lineToRelative(-7.98f, 6.19f)
                curveTo(6.51f, 42.62f, 14.62f, 48f, 24f, 48f)
                close()
            }
        }.build()

        return _GoogleIcon!!
    }

@Suppress("ObjectPropertyName")
private var _GoogleIcon: ImageVector? = null
