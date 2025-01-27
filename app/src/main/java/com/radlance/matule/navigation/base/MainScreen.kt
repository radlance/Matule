package com.radlance.matule.navigation.base

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.domain.user.User
import com.radlance.matule.navigation.bottom.BottomNavGraph
import com.radlance.matule.navigation.bottom.BottomNavigationBar
import com.radlance.matule.navigation.bottom.rememberNavigationState
import com.radlance.matule.navigation.drawer.DrawerMenu
import com.radlance.matule.navigation.drawer.DrawerStateViewModel
import com.radlance.matule.presentation.common.ProductViewModel
import com.radlance.matule.ui.theme.MatuleTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    onSignOut: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToNotification: () -> Unit,
    navigateToOrderHistory: () -> Unit,
    modifier: Modifier = Modifier,
    drawerStateViewModel: DrawerStateViewModel = hiltViewModel(),
    sharedProductViewModel: ProductViewModel = hiltViewModel()
) {
    val navigationState = rememberNavigationState()
    var userData by remember { mutableStateOf(User()) }

    val drawerState by drawerStateViewModel.drawerState.collectAsState()
    val signOutState by drawerStateViewModel.signOutState.collectAsState()

    val notificationsCount by drawerStateViewModel.hasNotifications.collectAsState()
    val userUiState by drawerStateViewModel.user.collectAsState()

    val updateAnim = updateTransition(drawerState, label = "MenuState")

    val scale by updateAnim.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        }, label = ""
    ) { it.getScale() }

    val transitionOffset by updateAnim.animateOffset(
        transitionSpec = {
            tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        }, label = ""
    ) { it.getOffset() }

    val roundness by updateAnim.animateDp(
        transitionSpec = {
            tween(durationMillis = 300)
        }, label = ""
    ) { it.getRoundness() }

    val rotate by updateAnim.animateFloat(
        transitionSpec = {
            tween(durationMillis = 300)
        },
        label = ""
    ) { it.getRotation() }

    userUiState.Show(
        onSuccess = { userData = it },
        onError = {},
        onLoading = {},
        onUnauthorized = {}
    )
    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (dragAmount > 10f) {
                        drawerStateViewModel.setExpandedState()
                    } else if (dragAmount < -10f) {
                        drawerStateViewModel.setCollapsedState()
                    }
                    drawerStateViewModel.getCurrentUserData()
                    change.consume()
                }
            }
    ) {
       DrawerMenu(
           user = userData,
           navigationState = navigationState,
           onMenuItemClick = drawerStateViewModel::changeDrawerState,
           onSignOutClick = drawerStateViewModel::signOut,
           signOutState = signOutState,
           onSignOut = {
               onSignOut()
               drawerStateViewModel.leaveHomeScreen()
           },
           notificationExist = notificationsCount > 0,
           navigateToCart = {},
           navigateToProfile = navigateToProfile,
           navigateToNotification = navigateToNotification,
           navigateToOrderHistory = navigateToOrderHistory,
       )

        Scaffold(
            containerColor = MaterialTheme.colorScheme.surfaceTint,
            bottomBar = {
                BottomNavigationBar(
                    navigationState,
                    navigateToCart = navigateToCart,
                    navigateToProfile = navigateToProfile,
                    navigateToNotification = navigateToNotification
                )
            },
            modifier = modifier
                .scale(scale)
                .offset {
                    IntOffset(
                        x = transitionOffset.x.toInt(),
                        y = transitionOffset.y.toInt()
                    )
                }
                .rotate(rotate)
                .clip(RoundedCornerShape(roundness))
        ) {
            BottomNavGraph(
                navigationState = navigationState,
                onDrawerClick = {
                    drawerStateViewModel.changeDrawerState()
                    drawerStateViewModel.getCurrentUserData()
                },
                modifier = Modifier,
                sharedViewModel = sharedProductViewModel
            )
        }
    }

}

@Preview
@Composable
private fun CommonBottomNavigationPreview() {
    MatuleTheme {
        MainScreen({}, {}, {}, {}, {})
    }
}

@Preview(device = "id:pixel_fold")
@Composable
private fun CommonBottomNavigationExpandedPreview() {
    MatuleTheme {
        MainScreen({}, {}, {}, {}, {})
    }
}