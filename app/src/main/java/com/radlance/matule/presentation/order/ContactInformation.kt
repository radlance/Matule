package com.radlance.matule.presentation.order

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun ContactIInformation(
    email: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.contact_information),
            fontSize = 14.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContactSection(
            iconResId = R.drawable.ic_mail,
            contentDescription = "ic_mail",
            contactData = email,
            title = stringResource(R.string.email),
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContactSection(
            iconResId = R.drawable.ic_phone,
            contentDescription = "ic_phone",
            contactData = "+234-811-732-5298",
            title = stringResource(R.string.phone),
        )
    }
}

@Composable
private fun ContactSection(
    @DrawableRes iconResId: Int,
    contentDescription: String?,
    contactData: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceTint)
        ) {
            Icon(painter = painterResource(iconResId), contentDescription = contentDescription)
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = contactData,
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                color = inputFieldTextColor,
                fontSize = 12.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(R.drawable.ic_edit),
            contentDescription = "ic_edit"
        )
    }
}

@Preview
@Composable
private fun ContactSSectionPreview() {
    MatuleTheme {
        ContactSection(
            iconResId = R.drawable.ic_mail,
            contentDescription = null,
            contactData = "emmanueloyiboke@gmail.com",
            title = "Email"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactInformationPreview() {
    MatuleTheme {
        ContactIInformation(email = "emmanueloyiboke@gmail.com")
    }
}