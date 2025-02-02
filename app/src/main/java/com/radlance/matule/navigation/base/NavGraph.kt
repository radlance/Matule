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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.radlance.matule.presentation.authorization.common.AuthViewModel
import com.radlance.matule.presentation.authorization.signin.AccountManager
import com.radlance.matule.presentation.authorization.signin.ForgotPasswordScreen
import com.radlance.matule.presentation.authorization.signin.SignInScreen
import com.radlance.matule.presentation.authorization.signin.VerificationScreen
import com.radlance.matule.presentation.authorization.signup.SignUpScreen
import com.radlance.matule.presentation.cart.CartScreen
import com.radlance.matule.presentation.common.ProductViewModel
import com.radlance.matule.presentation.history.HistoryScreen
import com.radlance.matule.presentation.home.catalog.CatalogScreen
import com.radlance.matule.presentation.home.details.ProductDetailsScreen
import com.radlance.matule.presentation.home.popular.PopularScreen
import com.radlance.matule.presentation.home.search.SearchScreen
import com.radlance.matule.presentation.notification.NotificationScreen
import com.radlance.matule.presentation.onboarding.OnboardingFirst
import com.radlance.matule.presentation.onboarding.OnboardingSecond
import com.radlance.matule.presentation.onboarding.OnboardingThird
import com.radlance.matule.presentation.onboarding.SplashScreen
import com.radlance.matule.presentation.map.MapScreen
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
    sharedViewModel: ProductViewModel = viewModel(),
    accountManager: AccountManager
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val navigationState by navigationViewModel.navigationState.collectAsState()

    val context = LocalContext.current

    val modifier = if (currentRoute in listOf(
            OnboardingFirst,
            OnboardingSecond,
            OnboardingThird
        ).map { it::class.qualifiedName } || currentRoute == null
    ) {
        Modifier.background(brush = backGroundGradient)
    } else {
        Modifier.background(color = MaterialTheme.colorScheme.background)
    }

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
                navigateToCart = { navController.navigate(Cart) },
                navigateToProfile = { navController.navigate(Profile) },
                navigateToNotification = { navController.navigate(Notification) },
                navigateToOrderHistory = { navController.navigate(History) },
                navigateToDetails = { navController.navigate(Details(it)) },
                navigateToSearch = { navController.navigate(Search) },
                navigateToCatalog = { navController.navigate(Catalog(it)) },
                navigateToPopular = { navController.navigate(Popular) },
                sharedProductViewModel = sharedViewModel
            )
        }

        composable<Popular> {
            PopularScreen(
                onBackPressed = { navController.navigate(Home) },
                navigateToFavorite = { navController.navigate(Home) },
                navigateToDetails = { navController.navigate(Details(it)) },
                navigateToCart = { navController.navigate(Cart) },
                productViewModel = sharedViewModel
            )
        }

        composable<Catalog> { backStackEntry ->
            val args = backStackEntry.toRoute<Catalog>()
            CatalogScreen(
                selectedCategoryId = args.categoryId,
                onBackPressed = { navController.navigate(Home) },
                navigateToDetails = { navController.navigate(Details(it)) },
                navigateToCart = { navController.navigate(Cart) },
                productViewModel = sharedViewModel
            )
        }

        composable<Search> {
            SearchScreen(
                onBackPressed = navController::navigateUp,
                onNavigateToCart = {
                    navController.navigate(Cart)
                },

                navigateToDetails = { navController.navigate(Details(it)) },
                productViewModel = sharedViewModel
            )
        }

        composable<Details> {
            val args = it.toRoute<Details>()
            ProductDetailsScreen(
                selectedProductId = args.productId,
                onBackPressed = navController::navigateUp,
                onNavigateToCart = { navController.navigate(Cart) },
                viewModel = sharedViewModel
            )
        }

        navigation<Payment>(startDestination = Cart) {
            composable<Cart> {
                CartScreen(
                    onPlaceOrderClick = { navController.navigate(Order) },
                    productViewModel = sharedViewModel,
                    onSignInClick = navigateToSignIn,
                    onBackPressed = { navController.navigate(Home) }
                )
            }

            composable<Order> {
                OrderScreen(
                    onBackPressed = {
                        navController.navigate(Cart) {
                            popUpTo<Order> { inclusive = true }
                        }
                    },
                    onMapClick = { lat, long -> navController.navigate(Map(lat, long)) },
                    navigateToCatalog = { navController.navigate(Home) },
                    productViewModel = sharedViewModel
                )
            }

            composable<Map> {
                val args = it.toRoute<Map>()
                MapScreen(latitude = args.latitude, longitude = args.longitude)
            }
        }

        composable<Notification> {
            NotificationScreen(onBackPressed = { navController.navigate(Home) })
        }

        navigation<Profile>(startDestination = UserData) {
            composable<UserData> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProfileScreen(
                        onBarcodeClick = { navController.navigate(Barcode) },
                        onEditProfileClick = { navController.navigate(EditProfile) },
                        onSignInClick = navigateToSignIn,
                        onBackPressed = { navController.navigate(Home) }
                    )
                }
            }

            composable<Barcode> {
                FullScreenBarcode(
                    onBackPressed = {
                        navController.navigate(UserData) {
                            popUpTo<Barcode> { inclusive = true }
                        }
                    }
                )
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
                onBackPressed = { navController.navigate(Home) },
                onSignInClick = navigateToSignIn
            )
        }
    }
}