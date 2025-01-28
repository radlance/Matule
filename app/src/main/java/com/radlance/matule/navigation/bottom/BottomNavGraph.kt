package com.radlance.matule.navigation.bottom

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.radlance.matule.presentation.common.ProductViewModel
import com.radlance.matule.presentation.favorite.FavoriteScreen
import com.radlance.matule.presentation.home.HomeScreen
import com.radlance.matule.presentation.home.details.ProductDetailsScreen
import com.radlance.matule.presentation.home.search.SearchScreen

@Composable
fun BottomNavGraph(
    navigationState: BottomNavigationState,
    onDrawerClick: () -> Unit,
    navigateToCart: () -> Unit,
    modifier: Modifier = Modifier,
    sharedViewModel: ProductViewModel = hiltViewModel()
) {
    val navController = navigationState.navHostController
    val context = LocalContext.current

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
                    onNavigateToCart = navigateToCart,
                    onNavigateToDetails = {
                        navController.navigate(Details(it))
                    },

                    onSearchFieldClick = {
                        navController.navigate(Search)
                    },
                    onCartClick = navigateToCart,
                    onMenuIconClick = onDrawerClick,
                    viewModel = sharedViewModel
                )
            }

            composable<Search> {
                SearchScreen(
                    onBackPressed = navController::navigateUp,
                    onNavigateToCart = {
                        navigationState.navigateTo(Payment)
                    },

                    onNavigateToDetails = {
                        navController.navigate(Details(it))
                    },
                    productViewModel = sharedViewModel
                )
            }

            composable<Details> {
                val args = it.toRoute<Details>()

                ProductDetailsScreen(
                    selectedProductId = args.productId,
                    onBackPressed = navController::navigateUp,
                    onNavigateToCart = navigateToCart,
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
                viewModel = sharedViewModel,
                onBackPressed = navController::navigateUp
            )
        }
    }
}