package com.radlance.matule.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class BottomNavigationState(
    val navHostController: NavHostController
) {
    fun <T : Any> navigateTo(route: T) {
        navHostController.navigate(route) {
            launchSingleTop = true
            restoreState = true

            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): BottomNavigationState {
    return remember {
        BottomNavigationState(navHostController)
    }
}