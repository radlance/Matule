package com.radlance.matule.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.EnterInputField
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp, top = 66.dp, bottom = 47.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val interactionSource = remember { MutableInteractionSource() }

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
            text = stringResource(R.string.forgot_password),
            fontSize = 32.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Bold,
            lineHeight = 38.sp,
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

        Button(
            onClick = {},
            modifier = modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(13.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = blueButtonColor,
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.send),
                fontSize = 14.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
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