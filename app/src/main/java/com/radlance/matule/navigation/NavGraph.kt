package com.radlance.matule.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radlance.matule.presentation.OnBoardingFirst
import com.radlance.matule.presentation.OnBoardingSecond
import com.radlance.matule.presentation.OnBoardingThird
import com.radlance.matule.presentation.SplashScreen
import com.radlance.matule.ui.theme.backGroundGradient

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Splash,
        modifier = Modifier.background(brush = backGroundGradient)
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