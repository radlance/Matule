package com.radlance.matule.ui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun ProfileNavigationIcon(color: Color): ImageVector {
    return ImageVector.Builder(
        name = "VuesaxOutlineFrame",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(fill = SolidColor(color)) {
            moveTo(12.15f, 11.62f)
            curveTo(12.12f, 11.62f, 12.1f, 11.62f, 12.07f, 11.62f)
            curveTo(12.02f, 11.61f, 11.95f, 11.61f, 11.89f, 11.62f)
            curveTo(8.99f, 11.53f, 6.8f, 9.25f, 6.8f, 6.43f)
            curveTo(6.8f, 3.57f, 9.13f, 1.25f, 11.99f, 1.25f)
            curveTo(14.85f, 1.25f, 17.18f, 3.57f, 17.18f, 6.43f)
            curveTo(17.17f, 9.25f, 14.97f, 11.53f, 12.18f, 11.62f)
            curveTo(12.17f, 11.62f, 12.16f, 11.62f, 12.15f, 11.62f)
            close()
            moveTo(11.99f, 2.75f)
            curveTo(9.96f, 2.75f, 8.3f, 4.41f, 8.3f, 6.43f)
            curveTo(8.3f, 8.43f, 9.86f, 10.05f, 11.85f, 10.12f)
            curveTo(11.9f, 10.1f, 12.04f, 10.1f, 12.17f, 10.12f)
            curveTo(14.13f, 10.03f, 15.67f, 8.42f, 15.68f, 6.43f)
            curveTo(15.68f, 4.41f, 14.02f, 2.75f, 11.99f, 2.75f)
            close()
        }
        path(fill = SolidColor(color)) {
            moveTo(12.16f, 22.55f)
            curveTo(10.2f, 22.55f, 8.23f, 22.05f, 6.74f, 21.05f)
            curveTo(5.35f, 20.12f, 4.59f, 18.87f, 4.59f, 17.5f)
            curveTo(4.59f, 16.12f, 5.35f, 14.86f, 6.74f, 13.93f)
            curveTo(9.74f, 11.93f, 14.6f, 11.93f, 17.58f, 13.93f)
            curveTo(18.96f, 14.85f, 19.73f, 16.11f, 19.73f, 17.48f)
            curveTo(19.73f, 18.85f, 18.97f, 20.12f, 17.58f, 21.05f)
            curveTo(16.08f, 22.05f, 14.12f, 22.55f, 12.16f, 22.55f)
            close()
            moveTo(7.57f, 15.18f)
            curveTo(6.61f, 15.83f, 6.09f, 16.65f, 6.09f, 17.51f)
            curveTo(6.09f, 18.36f, 6.62f, 19.18f, 7.57f, 19.81f)
            curveTo(10.06f, 21.48f, 14.26f, 21.48f, 16.75f, 19.81f)
            curveTo(17.71f, 19.17f, 18.23f, 18.35f, 18.23f, 17.49f)
            curveTo(18.23f, 16.64f, 17.7f, 15.81f, 16.75f, 15.18f)
            curveTo(14.26f, 13.53f, 10.06f, 13.53f, 7.57f, 15.18f)
            close()
        }
    }.build()
}