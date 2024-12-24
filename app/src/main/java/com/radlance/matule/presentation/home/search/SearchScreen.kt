package com.radlance.matule.presentation.home.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun SearchScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchFieldValue by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Spacer(Modifier.height(dimensionResource(R.dimen.main_top_padding)))
        SearchHeader(
            onBackPressed = {
                onBackPressed()
                keyboardController?.hide()
            }
        )
        Spacer(Modifier.height(26.dp))
        SearchField(
            value = searchFieldValue,
            onValueChange = { searchFieldValue = it },
            hint = stringResource(R.string.search),
            modifier = Modifier.padding(horizontal = 21.dp)
        )
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    MatuleTheme {
        SearchScreen({})
    }
}