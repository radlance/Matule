package com.radlance.matule.presentation.history

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HistoryList(
    history: List<HistoryProduct>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 19.dp, end = 21.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        history.groupByDate(context).forEach { (date, products) ->
            item {
                Text(
                    text = date,
                    fontSize = 18.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 22.sp
                )
            }

            items(
                items = products,
                key = { historyProduct -> historyProduct.id }) { historyProduct ->
                HistoryItem(historyProduct)
            }
            item { Spacer(Modifier.height(12.dp)) }
        }

    }
}

private fun List<HistoryProduct>.groupByDate(context: Context): Map<String, List<HistoryProduct>> {
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    val groupedHistoryByDate: Map<String, List<HistoryProduct>> = groupBy { historyProduct ->
        val localDate =
            historyProduct.orderTime.toLocalDateTime(TimeZone.currentSystemDefault()).date
        when (localDate) {
            currentDate -> context.getString(R.string.recently)
            currentDate.minus(1, DateTimeUnit.DAY) -> context.getString(R.string.yesterday)
            else -> localDate.toJavaLocalDate()
                .format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.getDefault()))
        }
    }

    return groupedHistoryByDate
}

@Preview(showBackground = true)
@Composable
private fun HistoryListPreview() {
    MatuleTheme {
        HistoryList(
            List(20) {
                HistoryProduct(
                    id = it,
                    title = "mock$it",
                    price = 100.0 * it,
                    imageUrl = "https://",
                    orderTime = Instant.fromEpochSeconds(Clock.System.now().epochSeconds - 86400 * it)
                )
            }
        )
    }
}