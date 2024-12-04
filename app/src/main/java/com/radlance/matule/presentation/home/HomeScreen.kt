package com.radlance.matule.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.fillRedColor
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.vector.BagIcon
import com.radlance.matule.ui.vector.Highlight05
import com.radlance.matule.ui.vector.MenuIcon

@Composable
fun HomeScreen(
    onBackPressed: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    var searchFieldValue by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()

    val loadContentResult by viewModel.catalogContent.collectAsState()
    val addToFavoriteResult by viewModel.favoriteResult.collectAsState()
    val addToCartResult by viewModel.inCartResult.collectAsState()

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            startContent = {
                IconButton(onClick = {}) {
                    Image(
                        imageVector = MenuIcon(MaterialTheme.colorScheme.onSurface),
                        contentDescription = "home_highlight_1"
                    )
                }
            },

            middleContent = {
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

        Spacer(Modifier.height(19.dp))

        HomeSearchBar(
            value = searchFieldValue,
            onValueChange = { searchFieldValue = it },
            onSettingsClick = {},
            hint = stringResource(R.string.search),
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(24.dp))

        addToFavoriteResult.Show(
            onSuccess = {},
            onLoading = { productId ->
                ChangeProductStatus(productId, viewModel::changeStateFavoriteStatus)
            },
            onError = { productId ->
                ChangeProductStatus(productId, viewModel::changeStateFavoriteStatus)
            }
        )

        addToCartResult.Show(
            onSuccess = {},
            onLoading = { productId ->
                ChangeProductStatus(productId, viewModel::changeStateInCartStatus)
            },
            onError = { productId ->
                ChangeProductStatus(productId, viewModel::changeStateInCartStatus)
            }
        )

        loadContentResult.Show(
            onSuccess = {
                CategoriesRow(
                    categories = it.categories
                )

                Spacer(Modifier.height(24.dp))
                PopularRow(
                    products = it.products,
                    onLikeClick = viewModel::changeFavoriteStatus,
                    onAddCartClick = viewModel::addProductToCart,
                    onCardClick = onNavigateToDetails,
                    onNavigateToCart = onNavigateToCart
                )
            },

            onLoading = { CircularProgressIndicator() },
            onError = {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = stringResource(R.string.load_error))
                        Button(onClick = viewModel::fetchContent) {
                            Text(stringResource(R.string.retry), color = Color.White)
                        }
                    }
                }
            }
        )

        Spacer(Modifier.height(40.dp))

        SaleBanner(modifier = Modifier.padding(horizontal = 20.dp))
    }
}

@Composable
private fun ChangeProductStatus(
    productId: Int?,
    onStatusChanged: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        productId?.let { onStatusChanged(productId) }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MatuleTheme(darkTheme = false) {
        HomeScreen({}, {}, {})
    }
}

@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
private fun HomeScreenExpandedPreview() {
    MatuleTheme(darkTheme = false) {
        HomeScreen({}, {}, {})
    }
}