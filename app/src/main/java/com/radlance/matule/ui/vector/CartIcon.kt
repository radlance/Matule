package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val CartIcon: ImageVector
    get() {
        if (_CartIcon != null) {
            return _CartIcon!!
        }
        _CartIcon = ImageVector.Builder(
            name = "Vector 1",
            defaultWidth = 20.dp,
            defaultHeight = 20.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(16f, 16f)
                curveTo(16.53f, 16f, 17.03f, 16.21f, 17.41f, 16.58f)
                curveTo(17.78f, 16.96f, 18f, 17.46f, 18f, 18f)
                curveTo(18f, 18.53f, 17.78f, 19.03f, 17.41f, 19.41f)
                curveTo(17.03f, 19.78f, 16.53f, 20f, 16f, 20f)
                curveTo(15.46f, 20f, 14.96f, 19.78f, 14.58f, 19.41f)
                curveTo(14.21f, 19.03f, 14f, 18.53f, 14f, 18f)
                curveTo(14f, 16.89f, 14.88f, 16f, 16f, 16f)
                close()
                moveTo(0f, 0f)
                lineTo(3.27f, 0f)
                lineTo(4.2f, 2f)
                lineTo(19f, 2f)
                curveTo(19.26f, 2f, 19.51f, 2.1f, 19.7f, 2.29f)
                curveTo(19.89f, 2.48f, 20f, 2.73f, 20f, 3f)
                curveTo(20f, 3.17f, 19.95f, 3.33f, 19.87f, 3.5f)
                lineTo(16.29f, 9.96f)
                curveTo(15.96f, 10.58f, 15.3f, 11f, 14.55f, 11f)
                lineTo(7.1f, 11f)
                lineTo(6.19f, 12.63f)
                lineTo(6.16f, 12.75f)
                curveTo(6.16f, 12.81f, 6.19f, 12.87f, 6.24f, 12.92f)
                curveTo(6.29f, 12.97f, 6.35f, 13f, 6.41f, 13f)
                lineTo(18f, 13f)
                lineTo(18f, 15f)
                lineTo(6f, 15f)
                curveTo(5.46f, 15f, 4.96f, 14.78f, 4.58f, 14.41f)
                curveTo(4.21f, 14.03f, 4f, 13.53f, 4f, 13f)
                curveTo(4f, 12.65f, 4.09f, 12.31f, 4.23f, 12.04f)
                lineTo(5.6f, 9.58f)
                lineTo(2f, 2f)
                lineTo(0f, 2f)
                lineTo(0f, 0f)
                close()
                moveTo(6f, 16f)
                curveTo(6.53f, 16f, 7.03f, 16.21f, 7.41f, 16.58f)
                curveTo(7.78f, 16.96f, 8f, 17.46f, 8f, 18f)
                curveTo(8f, 18.53f, 7.78f, 19.03f, 7.41f, 19.41f)
                curveTo(7.03f, 19.78f, 6.53f, 20f, 6f, 20f)
                curveTo(5.46f, 20f, 4.96f, 19.78f, 4.58f, 19.41f)
                curveTo(4.21f, 19.03f, 4f, 18.53f, 4f, 18f)
                curveTo(4f, 16.89f, 4.88f, 16f, 6f, 16f)
                close()
                moveTo(15f, 9f)
                lineTo(17.78f, 4f)
                lineTo(5.13f, 4f)
                lineTo(7.5f, 9f)
                lineTo(15f, 9f)
                close()
            }
        }.build()

        return _CartIcon!!
    }

@Suppress("ObjectPropertyName")
private var _CartIcon: ImageVector? = null
