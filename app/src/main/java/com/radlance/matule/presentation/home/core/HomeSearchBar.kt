package com.radlance.matule.presentation.home.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.vector.SearchSettingsIcon

@Composable
fun HomeSearchBar(
    onSettingsClick: () -> Unit,
    onSearchFieldClick: () -> Unit,
    modifier: Modifier = Modifier,
    hint: String = String()
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HomeSearchField(
            hint = hint,
            onClick = onSearchFieldClick,
            modifier = Modifier.weight(1f),
        )
        Spacer(Modifier.width(14.dp))
        IconButton(
            onClick = onSettingsClick, modifier = Modifier
                .clip(CircleShape)
                .size(52.dp)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                imageVector = SearchSettingsIcon,
                contentDescription = "SearchSettingsIcon",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun HomeSearchBarPreview() {
    MatuleTheme {
        HomeSearchBar(onSettingsClick = {}, onSearchFieldClick = {}, hint = "Поиск")
    }
}