package com.radlance.matule.presentation.home.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.domain.product.Category
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.ralewayFamily

@Composable
fun CategoriesRow(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    val allCategories = listOf(Category(id = 0, title = stringResource(R.string.all))) + categories
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.categories),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp,
            fontFamily = ralewayFamily,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 19.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(13.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = allCategories, key = { category -> category.id }) { categoryItem ->
                CategoryItem(categoryTitle = categoryItem.title, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoriesRowPreview() {
    val mockCategories = listOf(
        Category(id = 1, title = "Все"),
        Category(id = 2, title = "Outdoor"),
        Category(id = 3, title = "Tennis")
    )
    MatuleTheme {
        CategoriesRow(mockCategories)
    }
}

@Preview(showBackground = true, device = "spec:width=680px,height=1100px,dpi=480")
@Composable
private fun CategoriesRowSmallPreview() {
    val mockCategories = listOf(
        Category(id = 1, title = "Все"),
        Category(id = 2, title = "Outdoor"),
        Category(id = 3, title = "Tennis")
    )
    MatuleTheme {
        CategoriesRow(mockCategories)
    }
}