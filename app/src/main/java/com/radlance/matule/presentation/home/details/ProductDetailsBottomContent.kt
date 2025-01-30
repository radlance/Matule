package com.radlance.matule.presentation.home.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.NavigationButton
import com.radlance.matule.ui.theme.fillRedColor
import com.radlance.matule.ui.vector.BagIcon
import com.radlance.matule.ui.vector.CartIcon
import com.radlance.matule.ui.vector.FavoriteIcon
import com.radlance.matule.ui.vector.FavoriteOutlinedIcon

@Composable
fun ProductDetailsBottomContent(
    isFavorite: Boolean,
    inCart: Boolean,
    onLikeClick: () -> Unit,
    onCartClick: () -> Unit,
    onNavigateToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = onLikeClick,
            modifier = Modifier
                .padding(start = 9.dp, top = 3.dp)
                .clip(CircleShape)
                .size(52.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Image(
                imageVector = if (isFavorite) {
                    FavoriteIcon(
                        fillColor = fillRedColor,
                        width = 20.dp,
                        height = 17.79.dp
                    )
                } else {
                    FavoriteOutlinedIcon(MaterialTheme.colorScheme.onSurface)
                },
                contentDescription = "LikeIcon"
            )
        }

        Spacer(Modifier.width(40.dp))

        val buttonText = if (inCart) {
            R.string.move_to_cart
        } else {
            R.string.in_cart
        }

        val icon = if (inCart) {
            CartIcon
        } else {
            BagIcon(MaterialTheme.colorScheme.onSurface)
        }
        val action: () -> Unit = if(inCart) {
            onNavigateToCart
        } else {
            onCartClick
        }

        NavigationButton(
            stringResId = buttonText,
            onClick = action,
            icon = icon,
            modifier = Modifier
                .weight(1f)
                .height(52.dp)
        )

    }
}