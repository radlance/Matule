package com.radlance.matule.navigation.base

import androidx.navigation.NavController

interface OnBoardingNavigationState {
    fun navigate(navController: NavController)

    object NavigateToHome : OnBoardingNavigationState {
        override fun navigate(navController: NavController) {
            navController.navigate(Home) {
                popUpTo<Splash> { inclusive = true }
            }
        }
    }

    object NavigateToSignIn : OnBoardingNavigationState {
        override fun navigate(navController: NavController) {
            navController.navigate(SignIn) {
                popUpTo<Splash> { inclusive = true }
            }
        }
    }

    object NavigateToOnBoardingFirst : OnBoardingNavigationState {
        override fun navigate(navController: NavController) {
            navController.navigate(OnboardingFirst) {
                popUpTo<Splash> { inclusive = true }
            }
        }
    }
}