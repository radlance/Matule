package com.radlance.matule.presentation.signin

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.BackButton
import com.radlance.matule.presentation.component.EnterInputField
import com.radlance.matule.presentation.component.NavigationButton
import com.radlance.matule.ui.theme.blueButtonColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.theme.secondaryTextColor

@Composable
fun SignInScreen(
    onBackPressed: () -> Unit,
    onSignUpTextClicked: () -> Unit,
    onRecoverPasswordTextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    var emailFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var passwordFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    val interactionSource = remember { MutableInteractionSource() }

    BackHandler { onBackPressed() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(start = 20.dp, end = 20.dp, top = 66.dp, bottom = 47.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BackButton(onClicked = onBackPressed, modifier = Modifier.align(Alignment.Start))

        Text(
            text = stringResource(R.string.hello),
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

        Text(
            text = stringResource(R.string.recover),
            color = secondaryTextColor,
            fontSize = 12.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            lineHeight = 16.sp,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 12.dp)
                .clickable(indication = null, interactionSource = interactionSource) {
                    onRecoverPasswordTextClicked()
                }
        )
        NavigationButton(
            stringResId = R.string.sign_in,
            onClick = { },
            buttonColors = ButtonDefaults.buttonColors().copy(
                containerColor = blueButtonColor,
                contentColor = Color.White
            ),
            modifier = Modifier.padding(top = 24.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(Modifier.padding(top = 12.dp)) {
            Text(
                text = stringResource(R.string.is_first_time),
                color = secondaryTextColor,
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 1.sp
            )
            Text(
                text = stringResource(R.string.create_user),
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 1.sp,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) { onSignUpTextClicked() }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen({}, {}, {})
}

@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
private fun SignInScreenExpandedPreview() {
    SignInScreen({}, {}, {})
}