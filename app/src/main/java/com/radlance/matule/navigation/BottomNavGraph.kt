package com.radlance.matule.navigation

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radlance.matule.presentation.home.HomeScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Base,
        modifier = modifier
    ) {
        composable<Base> {
            HomeScreen(
                onBackPressed = {
                    (context as Activity).finish()
                }
            )
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