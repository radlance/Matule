package com.radlance.matule.presentation.authorization.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.theme.secondaryTextColor

@Composable
fun AgreementRow(
    onSwitchCheckBox: () -> Unit,
    onPdfLinkClick: () -> Unit,
    checked: Boolean,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = MutableInteractionSource()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(18.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.surfaceTint)
                .clickable(indication = null, interactionSource = interactionSource) {
                    onSwitchCheckBox()
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
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable(indication = null, interactionSource = interactionSource)  {
                    onPdfLinkClick()
                }
        )
    }
}

@Preview
@Composable
private fun AgreementRowPreview() {
    MatuleTheme {
        AgreementRow({}, {}, true)
    }
}