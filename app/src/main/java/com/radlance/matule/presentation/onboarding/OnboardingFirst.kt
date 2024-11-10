package com.radlance.matule.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
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
fun OnboardingFirst(
    onStartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = backGroundGradient)
    ) {
        Box(modifier = Modifier.padding(start = 50.dp, end = 50.dp, top = 121.dp)) {
            Image(
                painter = painterResource(R.drawable.onboarding_highlight_1),
                contentDescription = "onboarding_highlight_1",
                modifier = Modifier.offset(x = (-15).dp, y = (-15).dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.welcome),
                    fontSize = 30.sp,
                    color = Color.White,
                    lineHeight = 35.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(19.dp))

                Image(
                    painter = painterResource(R.drawable.onboarding_highlight_2),
                    contentDescription = "onboarding_highlight_2"
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box {
                Image(
                    painter = painterResource(R.drawable.onboarding_highlight_3),
                    contentDescription = "onboarding_highlight_3",
                    modifier = Modifier
                        .alpha(0.3f)
                        .padding(top = 89.dp, start = 45.dp),
                )

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(1.7f),
                    painter = painterResource(R.drawable.onboarding_image_1),
                    contentDescription = "onboaring_image_1",
                    contentScale = ContentScale.FillWidth
                )
            }
            Image(
                painter = painterResource(R.drawable.onboarding_progress_1),
                contentDescription = "onboarding_progress_1"
            )
        }
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.onboarding_highlight_4),
                        contentDescription = "onboarding_highlight_4",
                        modifier = Modifier
                            .alpha(0.3f)
                            .padding(start = 20.dp, top = 75.dp)
                            .rotate(-120f)
                    )
                }

                Image(
                    painter = painterResource(R.drawable.onboarding_highlight_4),
                    contentDescription = "onboarding_highlight_4",
                    modifier = Modifier
                        .alpha(0.3f)
                        .padding(end = 20.dp, bottom = 30.dp)
                )

            }
            OnBoardingButton(
                stringResId = R.string.start,
                onClick = onStartClicked
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingFirstPreview() {
    OnboardingFirst({})
}

@Preview(device = "spec:width=673dp,height=841dp")
@Composable
private fun OnboardingFirstPreviewExpanded() {
    OnboardingFirst({})
}