package com.radlance.matule.presentation.home.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.BackButton
import com.radlance.matule.presentation.component.CommonHeader
import com.radlance.matule.ui.theme.fillRedColor
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.vector.BagIcon

@Composable
fun DetailsHeader(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    CommonHeader(
        modifier = modifier
            .fillMaxWidth(),

        startContent = {
            BackButton(
                onClick = onBackPressed,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            )
        },
        middleContent = {
            Text(
                text = stringResource(R.string.sneaker_shop),
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )
        },
        endContent = {
            BadgedBox(
                badge = {
                    Badge(
                        modifier = Modifier.offset(x = (-2).dp, y = 5.dp),
                        contentColor = fillRedColor,
                        containerColor = fillRedColor
                    )
                }
            ) {
                IconButton(
                    onClick = {}, modifier = Modifier
                        .clip(CircleShape)
                        .size(44.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Icon(
                        imageVector = BagIcon(MaterialTheme.colorScheme.background),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = "home_highlight_1"
                    )
                }
            }
        }
    )
}