package com.radlance.matule.presentation.authorization.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
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
fun ForgotPasswordScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    var emailFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(start = 20.dp, end = 20.dp, top = 66.dp, bottom = 47.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        BackButton(onClicked = onBackPressed, modifier = Modifier.align(Alignment.Start))

        Text(
            text = stringResource(R.string.forgot_password),
            fontSize = 32.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Bold,
            lineHeight = 38.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 11.dp)
        )
        Text(
            text = stringResource(R.string.enter_your_account),
            fontSize = 16.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp,
            color = secondaryTextColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        EnterInputField(
            label = "",
            value = emailFieldValue,
            onValueChange = { emailFieldValue = it },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 40.dp),
            isPassword = false,
            interactionSource = interactionSource
        )

        NavigationButton(
            stringResId = R.string.send,
            onClick = {},
            buttonColors = ButtonDefaults.buttonColors().copy(
                containerColor = blueButtonColor,
                contentColor = Color.White
            ),
            modifier = Modifier.padding(top = 40.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen({})
}

@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
private fun ForgotPasswordScreenExpandedPreview() {
    ForgotPasswordScreen({})
}