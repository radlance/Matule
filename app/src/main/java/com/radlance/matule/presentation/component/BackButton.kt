package com.radlance.matule.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClicked,
        modifier = modifier
            .clip(CircleShape)
            .size(44.dp)
            .background(MaterialTheme.colorScheme.surfaceTint)
            .clickable { onClicked() },
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = "ArrowBackIosNew",
            modifier = Modifier.size(15.dp)
        )
    }
}