package com.radlance.matule.data.common

import com.radlance.matule.data.database.remote.entity.CategoryEntity
import com.radlance.matule.data.database.remote.entity.ProductEntity
import com.radlance.matule.domain.home.Category
import com.radlance.matule.domain.home.Product

abstract class Mapper {
    protected fun CategoryEntity.toCategory(): Category {
        return Category(id = id, title = title)
    }

    protected fun ProductEntity.toProduct(
        isFavorite: Boolean,
        inCart: Boolean
    ): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            description = description,
            imageUrl = imageUrl,
            isFavorite = isFavorite,
            inCart = inCart,
            categoryId = categoryId
        )
    }
}
