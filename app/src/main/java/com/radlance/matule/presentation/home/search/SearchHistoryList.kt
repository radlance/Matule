package com.radlance.matule.presentation.home.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.domain.search.SearchHistoryQuery
import com.radlance.matule.ui.theme.MatuleTheme
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime

@Composable
fun SearchHistoryList(
    history: List<SearchHistoryQuery>,
    onHistoryQueryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 140.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(items = history, key = { historyQuery -> historyQuery.id }) { historyQuery ->
            SearchHistoryItem(
                queryMessage = historyQuery.query,
                onHistoryQueryClick = onHistoryQueryClick
            )
        }
    }
}

@Preview
@Composable
private fun SearchHistoryListPreview() {
    MatuleTheme {
        SearchHistoryList(
            history = List(6) {
                SearchHistoryQuery(
                    query = "query$it",
                    queryTime = LocalDateTime.now().toKotlinLocalDateTime(),
                    id = it
                )
            },
            onHistoryQueryClick = {}
        )
    }
}