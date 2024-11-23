package com.radlance.matule.navigation

import androidx.navigation.NavController

interface NavigationState {
    fun navigate(navController: NavController)

    object NavigateToHome : NavigationState {
        override fun navigate(navController: NavController) {
            navController.navigate(Home) {
                popUpTo<Splash> { inclusive = true }
            }
        }
    }

    object NavigateToSignIn : NavigationState {
        override fun navigate(navController: NavController) {
            navController.navigate(SignIn) {
                popUpTo<Splash> { inclusive = true }
            }
        }
    }

    object NavigateToOnBoardingFirst : NavigationState {
        override fun navigate(navController: NavController) {
            navController.navigate(OnboardingFirst) {
                popUpTo<Splash> { inclusive = true }
            }
        }
    }
}