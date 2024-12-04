package com.radlance.matule.presentation.home.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.radlance.matule.R
import com.radlance.matule.domain.home.Product
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun ProductDetails(
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

        Image(
            painter = painterResource(R.drawable.slider_ellipse),
            contentDescription = "slider_ellipse",
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-40).dp)
        )
    }
}