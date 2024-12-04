package com.radlance.matule.presentation.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.radlance.matule.R
import com.radlance.matule.domain.home.Product
import com.radlance.matule.presentation.component.BackButton
import com.radlance.matule.presentation.component.NavigationButton
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.fillRedColor
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.moreDetailsColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.vector.BagIcon
import com.radlance.matule.ui.vector.LikeIcon

@Composable
fun ProductDetailsScreen(
    selectedProductId: Int,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val catalogContent by viewModel.catalogContent.collectAsState()


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(dimensionResource(R.dimen.main_top_padding)))
        HomeHeader(
            modifier = Modifier
                .fillMaxWidth(),

            startContent = {
                BackButton(onClicked = onBackPressed)
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

        Spacer(Modifier.height(26.dp))

        catalogContent.Show(
            onSuccess = { fetchContent ->
                val selectedProduct = fetchContent.products.first { it.id == selectedProductId }
                ProductDetails(selectedProduct)
                Image(
                    painter = painterResource(R.drawable.slider_ellipse),
                    contentDescription = "slider_ellipse",
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp)
                )
                Spacer(Modifier.height(10.dp))
                DetailsAdditionalRow(otherProducts = fetchContent.products)
                Spacer(Modifier.height(33.dp))

                var displayShowMoreDetails by remember { mutableStateOf(false) }
                var expanded by rememberSaveable { mutableStateOf(false) }

                Text(
                    text = selectedProduct.description,
                    fontSize = 14.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    color = inputFieldTextColor,
                    maxLines = if (expanded) {
                        Int.MAX_VALUE
                    } else {
                        3
                    },
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { textLayoutResult ->
                        if (textLayoutResult.didOverflowHeight) {
                            displayShowMoreDetails = true
                        }
                    },
                    modifier = Modifier.animateContentSize()
                )
                Spacer(Modifier.height(18.dp))

                if (displayShowMoreDetails) {
                    Text(
                        text = if (expanded) {
                            "Скрыть"
                        } else {
                            "Подробнее"
                        },
                        fontSize = 14.sp,
                        color = moreDetailsColor,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 21.sp,
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable {
                                expanded = !expanded
                            }
                    )

                    Spacer(Modifier.height(20.dp))
                }

                Spacer(Modifier.weight(1f))
            },
            onError = {},
            onLoading = {
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 140.dp)
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(start = 9.dp, top = 3.dp)
                    .clip(CircleShape)
                    .size(52.dp)
                    .background(MaterialTheme.colorScheme.surfaceTint),
            ) {
                val fillColor = Color.LightGray

                Image(
                    imageVector = LikeIcon(fillColor = fillColor, width = 20.dp, height = 17.79.dp),
                    contentDescription = "LikeIcon"
                )
            }

            Spacer(Modifier.width(40.dp))

            NavigationButton(
                stringResId = R.string.in_cart,
                onClick = {},
                icon = BagIcon(MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            )

        }
    }
}

@Composable
private fun ProductDetails(
    selectedProduct: Product,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = selectedProduct.title,
            fontSize = 26.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Men's Shoes",
            color = inputFieldTextColor,
            fontSize = 16.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Medium,
            lineHeight = 19.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "₽${selectedProduct.price}",
            fontSize = 24.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
        )

        AsyncImage(
            ImageRequest.Builder(context = LocalContext.current)
                .data(selectedProduct.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "shoe_example",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 21.dp)
        )
    }
}

@Preview
@Composable
private fun ProductDetailsScreenPreview() {
    MatuleTheme {
        ProductDetailsScreen(selectedProductId = 1, onBackPressed = {})
    }
}