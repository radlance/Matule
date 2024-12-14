package com.radlance.matule.presentation.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.ui.theme.MatuleTheme
import kotlinx.datetime.Clock

@Composable
fun HistoryList(
    history: List<HistoryProduct>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(start = 19.dp, end = 21.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = history, key = { historyProduct -> historyProduct.id }) { historyProduct ->
            HistoryItem(historyProduct)
        }
    }
}

@Preview
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
                    orderTime = Clock.System.now()
                )
            }
        )
    }
}