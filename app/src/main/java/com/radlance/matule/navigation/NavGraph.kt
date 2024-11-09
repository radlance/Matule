package com.radlance.matule.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radlance.matule.presentation.OnBoardingFirst
import com.radlance.matule.presentation.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Splash) {
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
            OnBoardingFirst()
        }
    }
}