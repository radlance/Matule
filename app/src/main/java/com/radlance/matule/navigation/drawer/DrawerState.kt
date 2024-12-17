package com.radlance.matule.navigation.drawer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface DrawerState {
    fun getScale(): Float
    fun getOffset(): Offset
    fun getRoundness(): Dp
    fun getRotation(): Float

    object Expanded : DrawerState {
        override fun getScale(): Float = 0.7f
        override fun getOffset(): Offset = Offset(750f, 60f)
        override fun getRoundness(): Dp = 25.dp
        override fun getRotation(): Float = -3.43f
    }

    object Collapsed : DrawerState {
        override fun getScale(): Float = 1f
        override fun getOffset(): Offset = Offset(0f, 0f)
        override fun getRoundness(): Dp = 0.dp
        override fun getRotation(): Float = 0f
    }
}