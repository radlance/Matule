package com.radlance.matule.navigation

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.radlance.matule.presentation.home.HomeScreen

@Composable
fun BottomNavGraph(
    navigationState: BottomNavigationState,
    modifier: Modifier = Modifier
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
                    onNavigateToCart = {
                        navController.navigate(Cart)
                    }
                )
            }

            composable<Cart> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "cart")
                }
            }
        }

        composable<Favorite> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(Favorite.toString())
            }
        }

        composable<Bag> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(Bag.toString())
            }
        }

        composable<Notification> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(Notification.toString())
            }
        }

        composable<Profile> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(Profile.toString())
            }
        }
    }
}