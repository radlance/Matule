package com.radlance.matule.navigation.drawer

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.ui.theme.fillRedColor
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun MenuItem(
    icon: ImageVector,
    contentDescription: String?,
    @StringRes sectionResId: Int,
    modifier: Modifier = Modifier,
    iconOffset: IntOffset = IntOffset(x = 0, y = 0),
    showBadge: Boolean = false,
    onItemClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onItemClick() }
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