package com.radlance.matule.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.radlance.matule.presentation.components.EnterInputField
import com.radlance.matule.ui.theme.blueButtonColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.theme.secondaryTextColor

@Composable
fun SignInScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    var emailFieldValue by rememberSaveable {
        mutableStateOf("")
    }

    var passwordFieldValue by rememberSaveable {
        mutableStateOf("")
    }
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
                .clickable { onBackPressed() }
        )

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
        )

        EnterInputField(
            label = stringResource(R.string.password),
            value = passwordFieldValue,
            onValueChange = { passwordFieldValue = it },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 30.dp),
            isPassword = true
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
        )
        Button(
            onClick = {},
            modifier = modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(13.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = blueButtonColor,
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                fontSize = 14.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row {
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
                lineHeight = 1.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen({})
}

@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
private fun SignInScreenExpandedPreview() {
    SignInScreen({})
}