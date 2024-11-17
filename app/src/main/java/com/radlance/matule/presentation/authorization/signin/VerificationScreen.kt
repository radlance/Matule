package com.radlance.matule.presentation.authorization.signin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.component.BackButton
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.inputFieldErrorBorderColor
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.theme.secondaryTextColor
import com.radlance.matule.ui.theme.verificationSubTextColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun VerificationScreen(
    onBackPressed: () -> Unit,
    viewModel: ForgotPasswordViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val focusRequesters = rememberSaveable { List(6) { FocusRequester() } }
    val coroutineScope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    var countdown by rememberSaveable { mutableIntStateOf(30) }
    var isTimerRunning by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(isTimerRunning) {
        if (isTimerRunning) {
            while (countdown > 0) {
                delay(1000L)
                countdown--
            }
            isTimerRunning = false
        }
    }

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
            text = stringResource(R.string.otp_check),
            fontSize = 32.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Bold,
            lineHeight = 38.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 11.dp)
        )
        Text(
            text = stringResource(R.string.check_your_email),
            fontSize = 16.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp,
            color = secondaryTextColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = stringResource(R.string.otp_code),
            fontSize = 21.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 25.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 14.dp, top = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(6) { index ->
                OtpInputItem(
                    value = viewModel.getOtpItem(index),
                    onValueChange = { otpValue ->
                        if (otpValue.length <= 1 && otpValue != " ") {
                            viewModel.updateOtpItem(index, otpValue)
                            if (otpValue.isNotEmpty() && index < 5) {
                                coroutineScope.launch {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            } else if (otpValue.isEmpty() && index > 0) {
                                coroutineScope.launch {
                                    focusRequesters[index - 1].requestFocus()
                                }
                            }
                        }
                    },
                    focusRequester = focusRequesters[index]
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.send_again),
                fontSize = 12.sp,
                color = verificationSubTextColor,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Normal,
                lineHeight = 14.sp,
                modifier = Modifier.clickable(enabled = !isTimerRunning) {
                    countdown = 30
                    isTimerRunning = true
                }
            )

            AnimatedVisibility(visible = isTimerRunning, enter = fadeIn(), exit = fadeOut()) {
                Text(
                    text = String.format(Locale.getDefault(), "00:%02d", countdown),
                    fontSize = 12.sp,
                    color = verificationSubTextColor,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Composable
private fun OtpInputItem(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    val shapeColor by animateColorAsState(
        targetValue = if (value.isEmpty() && isFocused) {
            inputFieldErrorBorderColor
        } else {
            Color.Transparent
        },
        label = "EnterInputField error"
    )

    Box(
        modifier = modifier
            .height(99.dp)
            .width(46.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceTint)
            .border(width = 1.dp, color = shapeColor, shape = RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = inputFieldTextColor,
                fontSize = 24.sp,
                lineHeight = 24.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
        )
    }
}

@Preview
@Composable
private fun VerificationScreenPreview() {
    MatuleTheme {
        VerificationScreen({})
    }
}

@Preview
@Composable
private fun OtpInputItemPreview() {
    val focusRequester = remember { FocusRequester() }
    MatuleTheme {
        OtpInputItem("0", {}, focusRequester)
    }
}