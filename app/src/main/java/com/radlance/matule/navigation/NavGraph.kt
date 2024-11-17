package com.radlance.matule.navigation

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.radlance.matule.presentation.authorization.signin.ForgotPasswordScreen
import com.radlance.matule.presentation.authorization.signin.SignInScreen
import com.radlance.matule.presentation.authorization.signin.VerificationScreen
import com.radlance.matule.presentation.authorization.signup.SignUpScreen
import com.radlance.matule.presentation.onboarding.OnBoardingViewModel
import com.radlance.matule.presentation.onboarding.OnboardingFirst
import com.radlance.matule.presentation.onboarding.OnboardingSecond
import com.radlance.matule.presentation.onboarding.OnboardingThird
import com.radlance.matule.presentation.onboarding.SplashScreen
import com.radlance.matule.ui.theme.backGroundGradient

@Composable
fun NavGraph(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route?.split(".")?.last()

    val onBoardingViewModel = viewModel<OnBoardingViewModel>()
    val onBoardingAlreadyViewed by onBoardingViewModel.alreadyViewed.collectAsState()

    val context = LocalContext.current

    val modifier = if (currentRoute in listOf(
            OnboardingFirst,
            OnboardingSecond,
            OnboardingThird
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
                    if (onBoardingAlreadyViewed) {
                        navController.navigate(SignIn) {
                            popUpTo<Splash> { inclusive = true }
                        }
                    } else {
                        navController.navigate(OnboardingFirst) {
                            popUpTo<Splash> { inclusive = true }
                        }
                    }
                }
            )
        }

        composable<OnboardingFirst> {
            OnboardingFirst(
                onStartClicked = {
                    navController.navigate(OnboardingSecond) {
                        popUpTo<OnboardingFirst> { inclusive = true }
                    }
                },
                onBackPressed = {
                    (context as Activity).finish()
                }
            )
        }

        composable<OnboardingSecond> {
            OnboardingSecond(
                onNextClicked = {
                    navController.navigate(OnboardingThird)
                },
                onBackPressed = {
                    navController.navigate(OnboardingFirst) {
                        popUpTo<OnboardingSecond> { inclusive = true }
                    }
                }
            )
        }

        composable<OnboardingThird> {
            OnboardingThird(
                onNextClicked = {
                    navController.navigate(SignIn) {
                        popUpTo<OnboardingThird> { inclusive = false }
                    }
                    onBoardingViewModel.setOnBoardingViewed()
                },
                onBackPressed = {
                    navController.navigate(OnboardingSecond) {
                        popUpTo<OnboardingThird> { inclusive = true }
                    }
                }
            )
        }

        composable<SignIn> {
            SignInScreen(
                onIconBackPressed = {
                    navController.navigate(OnboardingThird) {
                        popUpTo<SignIn> { inclusive = true }
                    }
                },

                onSignUpTextClicked = {
                    navController.navigate(SignUp) {
                        popUpTo<SignIn> { inclusive = true }
                    }
                },
                onRecoverPasswordTextClicked = {
                    navController.navigate(ForgotPassword)
                },
                onSuccessSignIn = {
                    navController.navigate(Home) {
                        popUpTo<SignIn> { inclusive = true }
                    }
                }
            )
        }

        composable<SignUp> {
            SignUpScreen(
                onBackPressed = {
                    navController.navigate(SignIn) {
                        popUpTo<SignUp> { inclusive = true }
                    }
                },
                onSignInTextClicked = {
                    navController.navigate(SignIn) {
                        popUpTo<SignUp> { inclusive = true }
                    }
                },
                onSuccessSignUp = {
                    navController.navigate(Home) {
                        popUpTo<SignUp> { inclusive = true }
                    }
                }
            )
        }

        composable<ForgotPassword> {
            ForgotPasswordScreen(
                onBackPressed = {
                    navController.navigateUp()
                },
                onSuccessSending = {
                    navController.navigate(Verification) {
                        popUpTo<ForgotPassword> { inclusive = true }
                    }
                }
            )
        }

        composable<Verification> {
            VerificationScreen(
                onBackPressed = {
                    navController.navigate(ForgotPassword) {
                        popUpTo<Verification> { inclusive = true }
                    }
                }
            )
        }

        composable<Home> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Home screen")
            }
        }
    }
}