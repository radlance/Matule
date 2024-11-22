package com.radlance.matule.presentation.home.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Highlight05: ImageVector
    get() {
        if (_Highlight05 != null) {
            return _Highlight05!!
        }
        _Highlight05 = ImageVector.Builder(
            name = "Highlight05",
            defaultWidth = 18.dp,
            defaultHeight = 19.dp,
            viewportWidth = 18f,
            viewportHeight = 19f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 0f
            ) {
                moveTo(0.5f, 0.5f)
                horizontalLineToRelative(17f)
                verticalLineToRelative(18f)
                horizontalLineToRelative(-17f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B2B2B)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(6.02f, 17.73f)
                curveTo(4.3f, 17.26f, 2.5f, 17.03f, 0.75f, 16.81f)
                curveTo(0.38f, 16.77f, 0.03f, 17.02f, 0f, 17.37f)
                curveTo(-0.05f, 17.73f, 0.22f, 18.05f, 0.57f, 18.1f)
                curveTo(2.26f, 18.3f, 4f, 18.52f, 5.64f, 18.97f)
                curveTo(5.99f, 19.07f, 6.37f, 18.87f, 6.47f, 18.52f)
                curveTo(6.58f, 18.18f, 6.37f, 17.82f, 6.02f, 17.73f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B2B2B)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(11.23f, 11.14f)
                curveTo(8.46f, 8.42f, 5.35f, 6.05f, 2.63f, 3.26f)
                curveTo(2.39f, 3f, 1.96f, 2.98f, 1.69f, 3.22f)
                curveTo(1.43f, 3.47f, 1.4f, 3.88f, 1.67f, 4.14f)
                curveTo(4.38f, 6.94f, 7.49f, 9.32f, 10.26f, 12.04f)
                curveTo(10.53f, 12.3f, 10.96f, 12.3f, 11.23f, 12.05f)
                curveTo(11.47f, 11.8f, 11.5f, 11.39f, 11.23f, 11.14f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF2B2B2B)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(16.41f, 0.68f)
                curveTo(16.49f, 2.23f, 16.57f, 3.79f, 16.65f, 5.35f)
                curveTo(16.65f, 5.7f, 16.97f, 5.98f, 17.35f, 5.96f)
                curveTo(17.73f, 5.94f, 18f, 5.64f, 18f, 5.28f)
                curveTo(17.91f, 3.72f, 17.83f, 2.17f, 17.75f, 0.61f)
                curveTo(17.73f, 0.25f, 17.4f, -0.02f, 17.03f, 0f)
                curveTo(16.68f, 0.02f, 16.38f, 0.32f, 16.41f, 0.68f)
                close()
            }
        }.build()

        return _Highlight05!!
    }

@Suppress("ObjectPropertyName")
private var _Highlight05: ImageVector? = null
