package com.radlance.matule.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.radlance.matule.presentation.OnBoardingFirst
import com.radlance.matule.presentation.OnBoardingSecond
import com.radlance.matule.presentation.OnBoardingThird
import com.radlance.matule.presentation.SplashScreen
import com.radlance.matule.ui.theme.backGroundGradient

@Composable
fun NavGraph(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route?.split(".")?.last()

    val modifier = if (currentRoute in listOf(
            OnBoardingFirst,
            OnBoardingSecond,
            OnBoardingThird
        ).map { it.toString() } || currentRoute == null
    ) {
        Modifier.background(brush = backGroundGradient)
    } else {
        Modifier.background(color = MaterialTheme.colorScheme.background)
    }

    NavHost(
        navController = navController,
        startDestination = Splash,
        modifier = modifier.fillMaxSize()
    ) {
        composable<Splash> {
            SplashScreen(
                onDelayFinished = {
                    navController.navigate(OnBoardingFirst) {
                        popUpTo<Splash> { inclusive = true }
                    }
                }
            )
        }

        composable<OnBoardingFirst> {
            OnBoardingFirst(
                onStartClicked = {
                    navController.navigate(OnBoardingSecond)
                }
            )
        }

        composable<OnBoardingSecond> {
            OnBoardingSecond(
                onNextClicked = {
                    navController.navigate(OnBoardingThird)
                }
            )
        }

        composable<OnBoardingThird> {
            OnBoardingThird(
                onNextClicked = {}
            )
        }
    }
}