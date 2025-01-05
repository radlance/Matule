package com.radlance.matule.navigation.base

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.radlance.matule.presentation.authorization.signin.ForgotPasswordScreen
import com.radlance.matule.presentation.authorization.signin.SignInScreen
import com.radlance.matule.presentation.authorization.signin.VerificationScreen
import com.radlance.matule.presentation.authorization.signup.SignUpScreen
import com.radlance.matule.presentation.onboarding.OnboardingFirst
import com.radlance.matule.presentation.onboarding.OnboardingSecond
import com.radlance.matule.presentation.onboarding.OnboardingThird
import com.radlance.matule.presentation.onboarding.SplashScreen
import com.radlance.matule.ui.theme.backGroundGradient

@Composable
fun NavGraph(
    navController: NavHostController,
    navigationViewModel: NavigationViewModel = viewModel()
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route?.split(".")?.last()
    val navigationState by navigationViewModel.navigationState.collectAsState()

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
                    navigationState.navigate(navController)
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
                    navigationViewModel.setOnBoardingViewed()
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
                    navController.navigate(ForgotPassword) {
                        popUpTo<SignIn> { inclusive = true }
                    }
                },

                onSuccessSignIn = {
                    navController.navigate(Home) {
                        popUpTo<SignIn> { inclusive = true }
                        navigationViewModel.setUserLoggedIn()
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
                        navigationViewModel.
                        setUserLoggedIn()
                    }
                }
            )
        }

        composable<ForgotPassword> {
            ForgotPasswordScreen(
                onBackPressed = {
                    navController.navigate(SignIn) {
                        popUpTo<ForgotPassword> { inclusive = true }
                    }
                },
                onSuccessSending = {
                    navController.navigate(Verification(email = it)) {
                        popUpTo<ForgotPassword> { inclusive = true }
                    }
                }
            )
        }

        composable<Verification> {
            val args = it.toRoute<Verification>()
            VerificationScreen(
                onBackPressed = {
                    navController.navigate(ForgotPassword) {
                        popUpTo<Verification> { inclusive = true }
                    }
                },
                email = args.email,
                onSuccessPasswordUpdating = {
                    navController.navigate(SignIn) {
                        popUpTo<Verification> { inclusive = true }
                    }
                }
            )
        }

        composable<Home> {
            MainScreen(
                onSignOut = {
                    navController.navigate(SignIn) {
                        popUpTo<Home> { inclusive = true }
                    }
                }
            )
        }
    }
}