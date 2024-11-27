package com.radlance.matule.data.common

import com.radlance.matule.data.database.entity.CategoryEntity
import com.radlance.matule.data.database.entity.ProductEntity
import com.radlance.matule.domain.home.Category
import com.radlance.matule.domain.home.Product

abstract class Mapper {
    protected fun CategoryEntity.toCategory(): Category {
        return Category(id = id, title = title)
    }

    protected fun ProductEntity.toProduct(): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            imageUrl = imageUrl,
            isFavorite = isFavorite,
            categoryId = categoryId
        )
    }

    protected fun Category.toCategoryEntity(): CategoryEntity {
        return CategoryEntity(id = id, title = title)
    }

    protected fun Product.toProductEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            title = title,
            price = price,
            imageUrl = imageUrl,
            isFavorite = isFavorite,
            categoryId = categoryId
        )
    }
}
