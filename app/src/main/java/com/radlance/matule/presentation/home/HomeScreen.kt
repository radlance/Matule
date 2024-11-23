package com.radlance.matule.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun HomeScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchFieldValue by rememberSaveable { mutableStateOf("") }

    BackHandler { onBackPressed() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeHeader(
            onMenuIconClicked = {},
            onCartIconClicked = {},
            hasNotification = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.main_top_padding))
        )

        Spacer(Modifier.height(19.dp))

        HomeSearchBar(
            value = searchFieldValue,
            onValueChange = { searchFieldValue = it },
            onSettingsClick = {},
            hint = stringResource(R.string.search)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MatuleTheme(darkTheme = false) {
        HomeScreen({})
    }
}

@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
private fun HomeScreenExpandedPreview() {
    MatuleTheme(darkTheme = false) {
        HomeScreen({})
    }
}