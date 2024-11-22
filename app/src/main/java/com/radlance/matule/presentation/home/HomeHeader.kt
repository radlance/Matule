package com.radlance.matule.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.home.vector.CartIcon
import com.radlance.matule.presentation.home.vector.Highlight05
import com.radlance.matule.presentation.home.vector.MenuIcon
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun HomeHeader(
    onMenuIconClicked: () -> Unit,
    onCartIconClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onMenuIconClicked) {
            Image(
                imageVector = MenuIcon(MaterialTheme.colorScheme.onSurface),
                contentDescription = "home_highlight_1"
            )
        }

        Box {
            Icon(
                imageVector = Highlight05,
                contentDescription = "Highlight05",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.offset(x = (-12).dp, y = (-7).dp)
            )
            Text(
                text = stringResource(R.string.main_screen),
                fontSize = 32.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 38.sp
            )
        }

            IconButton(
            onClick = onCartIconClicked, modifier = Modifier
                .clip(CircleShape)
                .size(44.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Icon(
                imageVector = CartIcon(MaterialTheme.colorScheme.background),
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = "home_highlight_1"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeHeaderPreview() {
    MatuleTheme {
        HomeHeader({}, {}, modifier = Modifier.fillMaxWidth())
    }
}