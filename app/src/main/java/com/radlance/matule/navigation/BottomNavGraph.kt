package com.radlance.matule.navigation

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.radlance.matule.presentation.cart.CartScreen
import com.radlance.matule.presentation.favorite.FavoriteScreen
import com.radlance.matule.presentation.history.HistoryScreen
import com.radlance.matule.presentation.home.HomeScreen
import com.radlance.matule.presentation.home.HomeViewModel
import com.radlance.matule.presentation.home.details.ProductDetailsScreen
import com.radlance.matule.presentation.order.OrderScreen

@Composable
fun BottomNavGraph(
    navigationState: BottomNavigationState,
    modifier: Modifier = Modifier
) {
    val navController = navigationState.navHostController
    val context = LocalContext.current

    val sharedViewModel = hiltViewModel<HomeViewModel>()
    NavHost(
        navController = navController,
        startDestination = Base,
        modifier = modifier
    ) {
        navigation<Base>(startDestination = Catalog) {
            composable<Catalog> {
                HomeScreen(
                    onBackPressed = {
                        (context as Activity).finish()
                    },
                    onNavigateToCart = {
                        navigationState.navigateTo(Payment)
                    },

                    onNavigateToDetails = {
                        navController.navigate(Details(it))
                    },
                    viewModel = sharedViewModel
                )
            }

            composable<Details> {
                val args = it.toRoute<Details>()

                ProductDetailsScreen(
                    selectedProductId = args.productId,
                    onBackPressed = navController::navigateUp,
                    onNavigateToCart = {
                        navigationState.navigateTo(Payment)
                    },
                    viewModel = sharedViewModel
                )
            }
        }

        composable<Favorite> {
            FavoriteScreen(
                onNavigateToCart = {
                    navigationState.navigateTo(Payment)
                },

                onNavigateToDetails = {
                    navController.navigate(Details(it))
                },
                viewModel = sharedViewModel
            )
        }

        navigation<Payment>(startDestination = Cart) {
            composable<Cart> {
                CartScreen(
                    onPlaceOrderClick = { navigationState.navigateTo(Order) },
                    viewModel = sharedViewModel
                )
            }

            composable<Order> {
                OrderScreen(
                    onBackPressed = { navigationState.navigateTo(Cart) },
                    navigateToCatalog = { navigationState.navigateTo(Catalog) },
                    viewModel = sharedViewModel
                )
            }
        }

        composable<Notification> {
            HistoryScreen()
        }

        composable<Profile> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(Profile.toString())
            }
        }
    }
}