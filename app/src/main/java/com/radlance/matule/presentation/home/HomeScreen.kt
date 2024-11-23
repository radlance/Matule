package com.radlance.matule.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun HomeScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBackPressed() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(top = dimensionResource(R.dimen.main_top_padding))) {
            HomeHeader(
                onMenuIconClicked = {},
                onCartIconClicked = {},
                hasNotification = false,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MatuleTheme(darkTheme = false) {
        HomeScreen({})
    }
}