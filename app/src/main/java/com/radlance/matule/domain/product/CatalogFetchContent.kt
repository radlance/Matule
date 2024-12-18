package com.radlance.matule.domain.product

data class CatalogFetchContent(
    val categories: List<Category>,
    val products: List<Product>
)
