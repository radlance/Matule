package com.radlance.matule.navigation.bottom

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.radlance.matule.presentation.common.ProductViewModel
import com.radlance.matule.presentation.favorite.FavoriteScreen
import com.radlance.matule.presentation.home.HomeScreen

@Composable
fun BottomNavGraph(
    navigationState: BottomNavigationState,
    onDrawerClick: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToDetails: (Int) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToCatalog: (Int?) -> Unit,
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
                    navigateToCart = navigateToCart,
                    navigateToDetails = navigateToDetails,
                    navigateToCatalog = navigateToCatalog,
                    onSearchFieldClick = navigateToSearch,
                    onCartClick = navigateToCart,
                    onMenuIconClick = onDrawerClick,
                    viewModel = sharedViewModel
                )
            }
        }

        composable<Favorite> {
            FavoriteScreen(
                onNavigateToCart = {
                    navigationState.navigateTo(Payment)
                },

                onNavigateToDetails = navigateToDetails,
                viewModel = sharedViewModel,
                onBackPressed = navController::navigateUp
            )
        }
    }
}