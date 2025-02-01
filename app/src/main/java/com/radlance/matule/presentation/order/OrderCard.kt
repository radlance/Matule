package com.radlance.matule.presentation.order

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.radlance.matule.presentation.common.PermissionViewModel
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun OrderCard(
    email: String,
    isGrantedLocationPermission: Boolean,
    modifier: Modifier = Modifier,
    permissionViewModel: PermissionViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .animateContentSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            ContactIInformation(
                email = email,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(12.dp))

            if (isGrantedLocationPermission) {
                val locationClientResultUiState by permissionViewModel.locationClientUiState.collectAsState()

                Text(
                    text = stringResource(R.string.address),
                    fontSize = 14.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(start = 20.dp, end = 32.dp)
                )

                locationClientResultUiState.Show(
                    onSuccess = { address ->
                        OrderAddress(
                            address = address.getAddressLine(0),
                            modifier = Modifier.padding(start = 20.dp, end = 32.dp)
                        )
                    },
                    onError = { message ->
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                )
            }

            Spacer(Modifier.height(12.dp))

            PaymentMethod(
                cardName = "Dbl Card",
                cardNumber = "1234 5678 0696 4629",
                modifier = Modifier.padding(start = 20.dp, end = 32.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun OrderCardPreview() {
    MatuleTheme {
        OrderCard(
            email = "emmanueloyiboke@gmail.com",
            isGrantedLocationPermission = true,
            modifier = Modifier
                .height(425.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(device = "spec:width=673dp,height=841dp")
@Composable
private fun OrderCardExpandedPreview() {
    MatuleTheme {
        OrderCard(
            email = "emmanueloyiboke@gmail.com",
            isGrantedLocationPermission = true,
            modifier = Modifier
                .height(425.dp)
                .fillMaxWidth()
        )
    }
}