package com.radlance.matule.navigation.base

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.navigation.bottom.BottomNavGraph
import com.radlance.matule.navigation.bottom.BottomNavigationBar
import com.radlance.matule.navigation.bottom.rememberNavigationState
import com.radlance.matule.navigation.drawer.DrawerMenu
import com.radlance.matule.navigation.drawer.DrawerStateViewModel
import com.radlance.matule.ui.theme.MatuleTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: DrawerStateViewModel = hiltViewModel()
) {
    val navigationState = rememberNavigationState()

    val drawerState by viewModel.drawerState.collectAsState()
    val user by viewModel.user.collectAsState()

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

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
       DrawerMenu(user)
        Scaffold(
            containerColor = MaterialTheme.colorScheme.surfaceTint,
            bottomBar = {
                BottomNavigationBar(navigationState)
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
                onDrawerClick = viewModel::changeDrawerState,
                modifier = Modifier
            )
        }
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