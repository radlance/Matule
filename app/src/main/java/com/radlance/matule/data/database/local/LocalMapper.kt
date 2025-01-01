package com.radlance.matule.data.database.local

import com.radlance.matule.data.database.local.entity.LocalCategoryEntity
import com.radlance.matule.data.database.local.entity.LocalNotificationEntity
import com.radlance.matule.data.database.local.entity.LocalProductEntity
import com.radlance.matule.data.database.local.entity.SearchHistoryQueryEntity
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.domain.product.Category
import com.radlance.matule.domain.product.Product
import com.radlance.matule.domain.search.SearchHistoryQuery

abstract class LocalMapper {
    protected fun SearchHistoryQueryEntity.toSearchHistoryQuery(): SearchHistoryQuery {
        return SearchHistoryQuery(query = query, queryTime = queryTime, id = id)
    }

    protected fun SearchHistoryQuery.toSearchHistoryQueryEntity(): SearchHistoryQueryEntity {
        return SearchHistoryQueryEntity(query = query, queryTime = queryTime)
    }

    protected fun LocalCategoryEntity.toCategory(): Category {
        return Category(title = title, id = id)
    }

    protected fun LocalProductEntity.toProduct(): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            description = description,
            imageUrl = imageUrl,
            categoryId = categoryId,
            isFavorite = isFavorite,
            quantityInCart = quantityInCart
        )
    }

    protected fun LocalNotificationEntity.toNotification(): Notification {
        return Notification(
            title = title,
            message = message,
            sendDate = sendDate,
            isRead = isRead,
            id = id
        )
    }
}