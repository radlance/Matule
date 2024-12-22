package com.radlance.matule.presentation.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.ui.theme.MatuleTheme
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(55.dp))
        NotificationHeader()
        Spacer(Modifier.height(20.dp))

        NotificationList(
            notifications = List(20) {
                Notification(
                    id = it,
                    title = "Заголовок$it",
                    content = LoremIpsum(25).values.single(),
                    sendDate = LocalDateTime.now().toKotlinLocalDateTime()
                )
            },
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Preview
@Composable
private fun NotificationScreenPreview() {
    MatuleTheme {
        NotificationScreen()
    }
}