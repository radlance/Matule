package com.radlance.matule.presentation.home

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun HomeScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    var searchFieldValue by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()

    val categories by viewModel.categories.collectAsState()
    val products by viewModel.products.collectAsState()

    BackHandler { onBackPressed() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(dimensionResource(R.dimen.main_top_padding)))
        HomeHeader(
            onMenuIconClicked = {},
            onCartIconClicked = {},
            hasNotification = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(19.dp))

        HomeSearchBar(
            value = searchFieldValue,
            onValueChange = { searchFieldValue = it },
            onSettingsClick = {},
            hint = stringResource(R.string.search),
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(24.dp))

        val context = LocalContext.current

        categories.Show(
            onSuccess = {
                CategoriesRow(
                    categories = it
                )
            },
            onError = {
                Toast.makeText(context, "failed load categories", Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(Modifier.height(24.dp))

        products.Show(
            onSuccess = {
                PopularRow(products = it, onLikeClicked = viewModel::switchFavoriteStatus)
            },
            onError = {
                Toast.makeText(context, "failed load products", Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(Modifier.height(40.dp))

        SaleBanner(modifier = Modifier.padding(horizontal = 20.dp))
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