package com.radlance.matule.navigation.base

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.radlance.matule.navigation.bottom.Barcode
import com.radlance.matule.navigation.bottom.Catalog
import com.radlance.matule.navigation.bottom.EditProfile
import com.radlance.matule.navigation.bottom.Order
import com.radlance.matule.navigation.bottom.Payment
import com.radlance.matule.navigation.bottom.UserData
import com.radlance.matule.presentation.authorization.common.AuthViewModel
import com.radlance.matule.presentation.authorization.signin.AccountManager
import com.radlance.matule.presentation.authorization.signin.ForgotPasswordScreen
import com.radlance.matule.presentation.authorization.signin.SignInScreen
import com.radlance.matule.presentation.authorization.signin.VerificationScreen
import com.radlance.matule.presentation.authorization.signup.SignUpScreen
import com.radlance.matule.presentation.cart.CartScreen
import com.radlance.matule.presentation.common.ProductViewModel
import com.radlance.matule.presentation.history.HistoryScreen
import com.radlance.matule.presentation.notification.NotificationScreen
import com.radlance.matule.presentation.onboarding.OnboardingFirst
import com.radlance.matule.presentation.onboarding.OnboardingSecond
import com.radlance.matule.presentation.onboarding.OnboardingThird
import com.radlance.matule.presentation.onboarding.SplashScreen
import com.radlance.matule.presentation.order.OrderScreen
import com.radlance.matule.presentation.profile.FullScreenBarcode
import com.radlance.matule.presentation.profile.ProfileScreen
import com.radlance.matule.presentation.profile.edit.EditProfileScreen
import com.radlance.matule.ui.theme.backGroundGradient

@Composable
fun NavGraph(
    navController: NavHostController,
    navigationViewModel: NavigationViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel(),
    accountManager: AccountManager
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

    val sharedViewModel = hiltViewModel<ProductViewModel>()
    val navigateToSignIn: () -> Unit = {
        navController.navigate(SignIn) {
            popUpTo<Home> { inclusive = true }
        }
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
                    navigationViewModel.setOnBoardingViewed(viewed = true)
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
                },
                viewModel = authViewModel.copy(),
                accountManager = accountManager
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
                },
                viewModel = authViewModel.copy()
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
                onSignOut = navigateToSignIn,
                navigateToCart = {
                    navController.navigate(Cart)
                },
                navigateToProfile = {
                    navController.navigate(Profile)
                },
                navigateToNotification = {
                    navController.navigate(Notification)
                },
                navigateToOrderHistory = {
                    navController.navigate(History)
                },
                sharedProductViewModel = sharedViewModel
            )
        }

        navigation<Payment>(startDestination = Cart) {
            composable<Cart> {
                CartScreen(
                    onPlaceOrderClick = { navController.navigate(Order) },
                    productViewModel = sharedViewModel,
                    onSignInClick = navigateToSignIn
                )
            }

            composable<Order> {
                OrderScreen(
                    onBackPressed = { navController.navigate(Cart) },
                    navigateToCatalog = { navController.navigate(Catalog) },
                    productViewModel = sharedViewModel
                )
            }
        }

        composable<Notification> {
            NotificationScreen()
        }

        navigation<Profile>(startDestination = UserData) {
            composable<UserData> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProfileScreen(
                        onBarcodeClick = { navController.navigate(Barcode) },
                        onEditProfileClick = { navController.navigate(EditProfile) },
                        onSignInClick = navigateToSignIn
                    )
                }
            }

            composable<Barcode> {
                FullScreenBarcode(onBackPressed = { navController.navigate(UserData) })
            }

            composable<EditProfile> {
                EditProfileScreen(
                    onBackPressed = { navController.navigate(UserData) },
                    onNavigateToProfile = { navController.navigate(UserData) }
                )
            }
        }

        composable<History> {
            HistoryScreen(
                viewModel = sharedViewModel,
                onSignInClick = navigateToSignIn
            )
        }
    }
}