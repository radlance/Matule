package com.radlance.matule.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.radlance.matule.ui.theme.MatuleTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navigationState = rememberNavigationState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceTint,
        bottomBar = {
            BottomNavigationBar(navigationState)
        },
        modifier = modifier
    ) {
        BottomNavGraph(
            navController = navigationState.navHostController,
            modifier = Modifier
        )
    }
}

@Preview
@Composable
private fun CommonBottomNavigationPreview() {
    MatuleTheme {
        MainScreen()
    }
}

@Preview(device = "id:pixel_fold")
@Composable
private fun CommonBottomNavigationExpandedPreview() {
    MatuleTheme {
        MainScreen()
    }
}