package com.radlance.matule.presentation.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.ui.theme.MatuleTheme
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime

@Composable
fun NotificationList(
    notifications: List<Notification>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 140.dp, top = 20.dp)
    ) {
        items(items = notifications, key = { notification -> notification.id }) { notification ->
            NotificationItem(notification = notification)
        }
    }
}

@Preview
@Composable
private fun NotificationListPreview() {
    MatuleTheme {
        NotificationList(
            notifications = List(20) {
                Notification(
                    id = it,
                    title = "Заголовок$it",
                    content = LoremIpsum(25).values.single(),
                    isRead = false,
                    sendDate = LocalDateTime.now().toKotlinLocalDateTime()
                )
            }
        )
    }
}