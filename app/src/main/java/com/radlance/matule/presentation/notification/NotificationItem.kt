package com.radlance.matule.presentation.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.secondaryTextColor
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NotificationItem(
    notification: Notification,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceTint)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = notification.title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            Text(text = notification.content, fontSize = 16.sp, fontWeight = FontWeight.Normal)
            Spacer(Modifier.height(16.dp))
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm")
            Text(
                text = notification.sendDate.toJavaLocalDateTime().format(formatter),
                color = secondaryTextColor
            )
        }
    }
}

@Preview
@Composable
private fun NotificationItemPreview() {
    MatuleTheme {
        NotificationItem(
            notification = Notification(
                title = "Заголовок",
                content = LoremIpsum(25).values.single(),
                isRead = true,
                sendDate = LocalDateTime.now().toKotlinLocalDateTime()
            )
        )
    }
}