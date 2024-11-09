package com.radlance.matule.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.backGroundGradient
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun OnBoardingSecond(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = backGroundGradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Box {
                Image(
                    painter = painterResource(R.drawable.onboarding_image_2),
                    contentDescription = "onboarding_image_2",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 36.dp),
                    contentScale = ContentScale.FillWidth
                )

                Image(
                    painter = painterResource(R.drawable.onboarding_highlight_4),
                    contentDescription = "onboarding_highlight_4",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 27.dp)
                )

                Image(
                    painter = painterResource(R.drawable.onboarding_highlight_3),
                    contentDescription = "onboarding_highlight_4",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 26.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(47.dp))
                Text(
                    text = stringResource(R.string.will_start_travel),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 44.2.sp
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.smart_collection_explain_now),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }

        }

    }
}

@Preview
@Composable
private fun OnBoardingSecondPreview() {
    OnBoardingSecond()
}

@Preview(device = "spec:width=673dp,height=841dp")
@Composable
private fun OnBoardingSecondPreviewExpanded() {
    OnBoardingSecond()
}