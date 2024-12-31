package com.radlance.matule.data.database.remote

import com.radlance.matule.data.database.remote.entity.RemoteCategoryEntity
import com.radlance.matule.data.database.remote.entity.HistoryEntity
import com.radlance.matule.data.database.remote.entity.NotificationEntity
import com.radlance.matule.data.database.remote.entity.RemoteProductEntity
import com.radlance.matule.data.database.remote.entity.UserEntity
import com.radlance.matule.domain.user.User
import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.domain.product.Category
import com.radlance.matule.domain.product.Product
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime

abstract class RemoteMapper {
    protected fun RemoteCategoryEntity.toCategory(): Category {
        return Category(id = id, title = title)
    }

    protected fun RemoteProductEntity.toProduct(
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

    protected fun HistoryEntity.toHistoryProduct(remoteProductEntity: RemoteProductEntity): HistoryProduct {
        return HistoryProduct(
            id = id,
            title = remoteProductEntity.title,
            price = remoteProductEntity.price,
            imageUrl = remoteProductEntity.imageUrl,
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
