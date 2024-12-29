package com.radlance.matule.data.database.remote

import com.radlance.matule.data.database.remote.entity.CategoryEntity
import com.radlance.matule.data.database.remote.entity.HistoryEntity
import com.radlance.matule.data.database.remote.entity.NotificationEntity
import com.radlance.matule.data.database.remote.entity.ProductEntity
import com.radlance.matule.data.database.remote.entity.UserEntity
import com.radlance.matule.domain.user.User
import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.domain.product.Category
import com.radlance.matule.domain.product.Product
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime

abstract class RemoteMapper {
    protected fun CategoryEntity.toCategory(): Category {
        return Category(id = id, title = title)
    }

    protected fun ProductEntity.toProduct(
        isFavorite: Boolean,
        quantityInCart: Int
    ): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            description = description,
            imageUrl = imageUrl,
            isFavorite = isFavorite,
            quantityInCart = quantityInCart,
            categoryId = categoryId
        )
    }

    protected fun Product.toHistoryEntity(userId: String): HistoryEntity {
        return HistoryEntity(
            userId = userId,
            productId = id,
            quantity = quantityInCart,
            orderDate = LocalDateTime.now().toKotlinLocalDateTime()
        )
    }

    protected fun HistoryEntity.toHistoryProduct(productEntity: ProductEntity): HistoryProduct {
        return HistoryProduct(
            id = id,
            title = productEntity.title,
            price = productEntity.price,
            imageUrl = productEntity.imageUrl,
            orderTime = orderDate
        )
    }

    protected fun UserEntity.toUser(): User {
        return User(name = name, imageUrl = imageUrl)
    }

    protected fun NotificationEntity.toNotification(): Notification {
        return Notification(
            title = title,
            content = message,
            sendDate = sendDate,
            isRead = isRead,
            id = id
        )
    }
}
