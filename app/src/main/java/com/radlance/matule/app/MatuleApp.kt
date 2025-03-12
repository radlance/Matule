package com.radlance.matule.app

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.radlance.matule.navigation.base.NavGraph
import com.radlance.matule.presentation.authorization.common.GoogleAccountManager

@Composable
fun MatuleApp(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        val navHostController = rememberNavController()

        val context = LocalContext.current
        val accountManager = GoogleAccountManager(context as ComponentActivity)
        NavGraph(
            navController = navHostController,
            accountManager = accountManager,
            paddingValues = innerPadding
        )
    }
}