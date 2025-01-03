package com.radlance.matule.presentation.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.NavigationButton
import com.radlance.matule.ui.theme.backGroundGradient
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun OnboardingThird(
    onNextClicked: () -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBackPressed() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = backGroundGradient)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if(dragAmount > 10f) {
                        onBackPressed()
                    } else if(dragAmount < -10f) {
                        onNextClicked()
                    }
                    change.consume()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Box {
                Image(
                    painter = painterResource(R.drawable.onboarding_image_3),
                    contentDescription = "onboarding_image_2",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 26.dp)
                        .scale(1.2f),
                    contentScale = ContentScale.FillWidth
                )

                Image(
                    painter = painterResource(R.drawable.onboarding_highlight_3),
                    contentDescription = "onboarding_highlight_4",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .alpha(0.7f)
                        .padding(start = 52.dp)
                        .size(75.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-15).dp)
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.you_have_the_power),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 44.2.sp
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.in_your_room_a_lot_of_plants),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(37.5.dp))
                Image(
                    painter = painterResource(R.drawable.onboarding_progress_3),
                    contentDescription = "onboarding_progress_3"
                )
            }


        }

        NavigationButton(
            stringResId = R.string.next,
            onClick = onNextClicked,
            buttonColors = ButtonDefaults.buttonColors().copy(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 36.dp, top = 16.dp)
        )
    }
}

@Preview
@Composable
private fun OnboardingThirdScreenPreview() {
    OnboardingThird({}, {})
}


@Preview(device = "spec:width=673dp,height=841dp")
@Composable
private fun OnboardingThirdPreviewExpanded() {
    OnboardingThird({}, {})
}