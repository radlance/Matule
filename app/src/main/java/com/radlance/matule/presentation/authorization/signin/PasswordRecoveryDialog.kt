package com.radlance.matule.presentation.authorization.signin

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.presentation.component.EnterInputField
import com.radlance.matule.presentation.component.NavigationButton
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.poppinsFamily

@Composable
fun PasswordRecoveryDialog(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val scrollState = rememberScrollState()

    val clipBoardManager = LocalClipboardManager.current

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors()
            .copy(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = stringResource(R.string.enter_the_quote),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            )
            EnterInputField(
                value = value,
                onValueChange = { onValueChanged(it) },
                interactionSource = interactionSource,
                modifier = Modifier.padding(top = 8.dp)
            )

            Row {
                NavigationButton(
                    stringResId = R.string.generate_password,
                    onClick = {},
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(60.dp)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                NavigationButton(
                    stringResId = R.string.copy,
                    onClick = { clipBoardManager.setText(AnnotatedString(value)) },
                    enabled = value.isNotEmpty(),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(60.dp)
                        .weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PasswordRecoveryDialogPreview() {
    MatuleTheme {
        PasswordRecoveryDialog(value = "", onValueChanged = {})
    }
}