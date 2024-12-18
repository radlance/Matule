package com.radlance.matule.navigation.drawer

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.domain.authorization.User
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.componentGrayColor
import com.radlance.matule.ui.theme.fillRedColor
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.vector.CartIcon
import com.radlance.matule.ui.vector.ExitIcon
import com.radlance.matule.ui.vector.FavoriteNavigationIcon
import com.radlance.matule.ui.vector.NotificationNavigationIcon
import com.radlance.matule.ui.vector.OrdersIcon
import com.radlance.matule.ui.vector.ProfileNavigationIcon
import com.radlance.matule.ui.vector.SettingsIcon

@Composable
fun DrawerMenu(
    user: User,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 30.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(78.dp))
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = user.name,
            fontSize = 20.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Bold,
            lineHeight = 23.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(55.dp))

        Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {
            MenuItem(
                icon = ProfileNavigationIcon(Color.White),
                contentDescription = "ProfileNavigationIcon",
                sectionResId = R.string.profile
            )

            MenuItem(
                icon = CartIcon,
                contentDescription = "CartIcon",
                sectionResId = R.string.cart
            )

            MenuItem(
                icon = FavoriteNavigationIcon(Color.White),
                contentDescription = "CartIcon",
                sectionResId = R.string.favorite
            )

            MenuItem(
                icon = OrdersIcon,
                contentDescription = "OrdersIcon",
                sectionResId = R.string.orders,
                iconOffset = IntOffset(x = 0, y = 15)
            )

            MenuItem(
                icon = NotificationNavigationIcon(Color.White),
                contentDescription = "NotificationIcon",
                sectionResId = R.string.notifications
            )

            MenuItem(
                icon = SettingsIcon,
                contentDescription = "SettingsIcon",
                sectionResId = R.string.settings
            )
        }

        Spacer(Modifier.height(38.dp))
        HorizontalDivider(color = componentGrayColor, modifier = Modifier.alpha(0.23f))
        Spacer(Modifier.height(30.dp))

        MenuItem(
            icon = ExitIcon,
            contentDescription = "ExitIcon",
            sectionResId = R.string.log_out
        )
    }
}

@Composable
private fun MenuItem(
    icon: ImageVector,
    contentDescription: String?,
    @StringRes sectionResId: Int,
    modifier: Modifier = Modifier,
    iconOffset: IntOffset = IntOffset(x = 0, y = 0),
    showBadge: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(modifier = Modifier.width(25.dp), contentAlignment = Alignment.Center) {

            BadgedBox(
                badge = {
                    if (showBadge) {
                        Badge(
                            modifier = Modifier.size(8.dp).offset(x = 1.dp, y = (-6).dp),
                            containerColor = fillRedColor,
                            contentColor = fillRedColor
                        )
                    }
                }
            ) {
                Icon(
                    imageVector = icon,
                    tint = Color.White,
                    contentDescription = contentDescription,
                    modifier = Modifier.offset { iconOffset }
                )
            }
        }
        Spacer(Modifier.width(25.dp))
        Text(
            text = stringResource(sectionResId),
            fontSize = 16.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun DrawerMenuPreview() {
    MatuleTheme {
        DrawerMenu(User(name = "stub"))
    }
}

@Preview
@Composable
private fun MenuItemPreview() {
    MatuleTheme {
        MenuItem(
            icon = NotificationNavigationIcon(Color.White),
            contentDescription = null,
            R.string.retry,
            showBadge = true
        )
    }
}