package com.radlance.matule.presentation.component

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.fillRedColor
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun EnterInputField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes hintResId: Int,
    interactionSource: MutableInteractionSource,
    modifier: Modifier = Modifier,
    label: String = "",
    isPassword: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    showCheck: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    var showPassword by rememberSaveable {
        mutableStateOf(!isPassword)
    }

    val shapeColor by animateColorAsState(
        targetValue = if(isError) {
            fillRedColor
        } else {
            Color.Transparent
        },
        label = "EnterInputField error"
    )

    Column(modifier = modifier) {
        if (label.isNotBlank()) {
            Text(
                text = label,
                fontSize = 16.sp,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surfaceTint)
                .border(width = 1.dp, color = shapeColor, shape = RoundedCornerShape(14.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(hintResId),
                            color = inputFieldTextColor,
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicTextField(
                            value = value,
                            onValueChange = onValueChange,
                            modifier = Modifier.weight(1f),
                            visualTransformation = if (!showPassword) {
                                PasswordVisualTransformation()
                            } else {
                                VisualTransformation.None
                            },
                            enabled = enabled,
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 16.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Medium
                            ),
                            keyboardOptions = keyboardOptions,
                        )

                        if (isPassword) {
                            Icon(
                                painter = painterResource(R.drawable.ic_visibility_off),
                                contentDescription = "VisibilityOff",
                                modifier = Modifier
                                    .clickable(
                                        indication = null,
                                        interactionSource = interactionSource
                                    ) {
                                        showPassword = !showPassword
                                    }
                            )
                        }

                        if (showCheck) {
                            Image(
                                painter = painterResource(R.drawable.ic_done),
                                contentDescription = "ic_done"
                            )
                        }
                    }
                }
            }
        }
    }
}


@SuppressLint("UnrememberedMutableInteractionSource")
@Preview
@Composable
private fun EnterInputFieldHintPreview() {
    MatuleTheme {
        EnterInputField(
            label = "",
            value = "",
            onValueChange = {},
            hintResId = R.string.name,
            enabled = false,
            interactionSource = MutableInteractionSource()
        )
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Preview
@Composable
private fun EnterInputFieldValuePreview() {
    MatuleTheme {
        EnterInputField(
            label = "",
            value = "value",
            onValueChange = {},
            hintResId = R.string.name,
            enabled = false,
            interactionSource = MutableInteractionSource()
        )
    }
}