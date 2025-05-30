package com.radlance.matule.navigation.bottom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.radlance.matule.ui.theme.bluePrimaryColor
import com.radlance.matule.ui.theme.secondaryTextColor
import com.radlance.matule.ui.vector.BagIcon
import com.radlance.matule.ui.vector.BottomPanel
import com.radlance.matule.ui.vector.FavoriteOutlinedIcon
import com.radlance.matule.ui.vector.HomeNavigationIcon
import com.radlance.matule.ui.vector.NotificationNavigationIcon
import com.radlance.matule.ui.vector.ProfileNavigationIcon

@Composable
fun BottomNavigationBar(
    bottomInnerPadding: Dp,
    navigationState: BottomNavigationState,
    navigateToCart: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToNotification: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .offset(y = 100.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = bottomInnerPadding)
    ) {
        Image(
            imageVector = BottomPanel(MaterialTheme.colorScheme.surfaceVariant),
            contentDescription = "BottomPanel",
            modifier = Modifier
                .fillMaxWidth()
                .height(106.dp)
                .scale(scaleX = 1.1f, scaleY = 1.1f)
                .offset(y = 6.dp),
            contentScale = ContentScale.FillWidth
        )

        IconButton(
            onClick = navigateToCart,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(56.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                imageVector = BagIcon(Color.White),
                contentDescription = "BagIcon",
                tint = Color.White
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp, start = 31.dp, end = 31.dp),
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        if (!isSelectedIcon(Base, navBackStackEntry)) {
                            navigationState.navigateTo(Base)
                        }
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(Modifier.weight(1f)) {
                    Image(
                        imageVector = HomeNavigationIcon(
                            getNavigationItemColor(
                                Base,
                                navBackStackEntry
                            )
                        ),
                        contentDescription = "HomeNavigationIcon"
                    )
                }

                Row(
                    Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            if (!isSelectedIcon(Favorite, navBackStackEntry)) {
                                navigationState.navigateTo(Favorite)
                            }
                        }, horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        imageVector = FavoriteOutlinedIcon(
                            getNavigationItemColor(
                                Favorite,
                                navBackStackEntry
                            ),
                        ),
                        contentDescription = "FavoriteNavigationIcon"
                    )
                }

            }
            Spacer(Modifier.weight(2f))
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            navigateToNotification()
                        }
                ) {
                    Image(
                        imageVector = NotificationNavigationIcon(secondaryTextColor),
                        contentDescription = "NotificationNavigationIcon"
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            navigateToProfile()
                        }
                ) {
                    Image(
                        imageVector = ProfileNavigationIcon(secondaryTextColor),
                        contentDescription = "ProfileNavigationIcon",
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

private fun getNavigationItemColor(
    navigationItem: BottomDestination,
    navBackStackEntry: NavBackStackEntry?
): Color {
    return if (isSelectedIcon(navigationItem, navBackStackEntry)) {
        bluePrimaryColor
    } else {
        secondaryTextColor
    }
}

private fun isSelectedIcon(
    navigationItem: BottomDestination,
    navBackStackEntry: NavBackStackEntry?
): Boolean {
    return navBackStackEntry?.destination?.hierarchy?.any {
        it.route == navigationItem::class.qualifiedName
    } ?: false
}
