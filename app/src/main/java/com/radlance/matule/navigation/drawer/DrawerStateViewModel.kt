package com.radlance.matule.navigation.drawer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DrawerStateViewModel : ViewModel() {
    private val _drawerState = MutableStateFlow<DrawerState>(DrawerState.Collapsed)
    val drawerState: StateFlow<DrawerState>
        get() = _drawerState

    fun changeDrawerState() {
        _drawerState.value = if (drawerState.value is DrawerState.Expanded) {
            DrawerState.Collapsed
        } else {
            DrawerState.Expanded
        }
    }
}