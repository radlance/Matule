package com.radlance.matule.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.EnterInputField
import com.radlance.matule.ui.theme.blueButtonColor
import com.radlance.matule.ui.theme.componentGrayColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.theme.secondaryTextColor

@Composable
fun SignUpScreen(
    onBackPressed: () -> Unit,
    onSignInTextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    var nameFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var emailFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var passwordFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var checked by rememberSaveable {
        mutableStateOf(false)
    }

    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp, top = 66.dp, bottom = 47.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.ic_arrow_back),
            contentDescription = "ic_arrow_back",
            modifier = Modifier
                .align(Alignment.Start)
                .clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) { onBackPressed() }
        )

        Text(
            text = stringResource(R.string.registration),
            fontSize = 32.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Bold,
            lineHeight = 38.sp,
            modifier = Modifier.padding(top = 11.dp)
        )
        Text(
            text = stringResource(R.string.fill_your_data),
            fontSize = 16.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp,
            color = secondaryTextColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        EnterInputField(
            label = stringResource(R.string.your_name),
            value = nameFieldValue,
            onValueChange = { nameFieldValue = it },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 30.dp),
            isPassword = false,
            interactionSource = interactionSource
        )

        EnterInputField(
            label = stringResource(R.string.email),
            value = emailFieldValue,
            onValueChange = { emailFieldValue = it },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 30.dp),
            isPassword = false,
            interactionSource = interactionSource
        )

        EnterInputField(
            label = stringResource(R.string.password),
            value = passwordFieldValue,
            onValueChange = { passwordFieldValue = it },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 30.dp),
            isPassword = true,
            interactionSource = interactionSource
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(18.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(componentGrayColor)
                    .clickable(indication = null, interactionSource = interactionSource) {
                        checked = !checked
                    }
            ) {
                if (checked) {
                    Image(
                        painter = painterResource(R.drawable.ic_agreement),
                        contentDescription = "ic_agreement"
                    )
                }
            }

            Text(
                text = stringResource(R.string.agreement),
                fontSize = 16.sp,
                color = secondaryTextColor,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Button(
            onClick = {},
            modifier = modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(13.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = blueButtonColor,
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.register),
                fontSize = 14.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row {
            Text(
                text = stringResource(R.string.have_an_account),
                color = secondaryTextColor,
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 1.sp
            )
            Text(
                text = stringResource(R.string.sign_in),
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 1.sp,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) { onSignInTextClicked() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen({}, {})
}

@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
private fun SignUpScreenExpandedPreview() {
    SignUpScreen({}, {})
}