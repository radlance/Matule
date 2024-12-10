package com.radlance.matule.presentation.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.poppinsFamily
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun PaymentMethod(
    cardName: String,
    cardNumber: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.payment_method),
            fontSize = 14.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp
        )
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceTint)
            ) {
                Image(
                    painter = painterResource(R.drawable.dbl_card),
                    contentDescription = "dbl_card",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text(
                    text = cardName,
                    fontSize = 14.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = maskString(cardNumber),
                    fontSize = 12.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    color = inputFieldTextColor
                )
            }

            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = Icons.Filled.ExpandMore,
                "ExpandMore",
                tint = inputFieldTextColor
            )
        }
    }
}

private fun maskString(input: String): String {
    val parts = input.split(" ")
    val maskedParts = parts.take(2).map { "****" } + parts.drop(2)
    return maskedParts.joinToString(" ")
}

@Preview
@Composable
private fun PaymentMethodPreview() {
    MatuleTheme {
        PaymentMethod(cardName = "Dbl Card", cardNumber = "1234 5678 0696 4629")
    }
}