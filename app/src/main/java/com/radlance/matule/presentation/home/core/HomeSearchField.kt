package com.radlance.matule.presentation.home.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.poppinsFamily

@Composable
fun HomeSearchField(
    hint: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .height(52.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "ic_search",
                modifier = Modifier.padding(5.dp)
            )

            Spacer(Modifier.width(12.dp))

            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = hint,
                    fontSize = 12.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    color = inputFieldTextColor
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeSearchFieldPreview() {
    MatuleTheme {
        HomeSearchField(
            hint = "Поиск",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}