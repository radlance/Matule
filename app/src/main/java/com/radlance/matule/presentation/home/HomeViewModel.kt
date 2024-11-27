package com.radlance.matule.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.home.Category
import com.radlance.matule.domain.home.HomeRepository
import com.radlance.matule.domain.home.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    val categories = homeRepository.getCategories().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    val products = homeRepository.getProducts().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    init {
        initCategories()
    }

    private fun initCategories() {
        viewModelScope.launch {
            if (homeRepository.getCategories().first().isEmpty()) {
                homeRepository.addCategories(
                    listOf(
                        Category(title = "Outdoor"),
                        Category(title = "Running"),
                        Category(title = "Tennis"),
                    )
                )

                homeRepository.addProducts(
                    listOf(
                        Product(
                            title = "Nike Jordan",
                            price = 302.00,
                            imageUrl = "https://osoknxtwcppulkimwpjo.supabase.co/storage/v1/object/sign/Matule/nike_jordan.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJNYXR1bGUvbmlrZV9qb3JkYW4ucG5nIiwiaWF0IjoxNzMyNjM2NzAyLCJleHAiOjE3NjQxNzI3MDJ9.b3lgKnS4lKwHii7qpx4RuMQZGW7xAmz52JlXVlY7q08&t=2024-11-26T15%3A58%3A22.158Z",
                            isFavorite = true,
                            categoryId = 1
                        ),
                        Product(
                            title = "Nike Air Max",
                            price = 752.00,
                            imageUrl = "https://osoknxtwcppulkimwpjo.supabase.co/storage/v1/object/sign/Matule/nike_air_max.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJNYXR1bGUvbmlrZV9haXJfbWF4LnBuZyIsImlhdCI6MTczMjYzNjg0NiwiZXhwIjoxNzY0MTcyODQ2fQ.wnlmnENw_50Cg5s8wl-NE2j7V881eK-3jIp250Q9MI4&t=2024-11-26T16%3A00%3A46.581Z",
                            isFavorite = false,
                            categoryId = 2
                        ),
                        Product(
                            title = "Nike Air Max",
                            price = 477.00,
                            imageUrl = "https://osoknxtwcppulkimwpjo.supabase.co/storage/v1/object/sign/Matule/nike_club_max.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJNYXR1bGUvbmlrZV9jbHViX21heC5wbmciLCJpYXQiOjE3MzI2MzY5MjksImV4cCI6MTc2NDE3MjkyOX0.30rk9-JgvMdVhSZ5x8SCUXGxA8HNLfJzj-bQj0T3XnQ&t=2024-11-26T16%3A02%3A09.087Z",
                            isFavorite = false,
                            categoryId = 3
                        ),
                        Product(
                            title = "Nike Air Max 200",
                            price = 576.00,
                            imageUrl = "https://osoknxtwcppulkimwpjo.supabase.co/storage/v1/object/sign/Matule/nike_air_max_200.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJNYXR1bGUvbmlrZV9haXJfbWF4XzIwMC5wbmciLCJpYXQiOjE3MzI2MzY5NzQsImV4cCI6MTc2NDE3Mjk3NH0.OdA0Bi4odRrdRxFi9h-HFEshmXEAGpeuERgtz2Z0BWw&t=2024-11-26T16%3A02%3A54.229Z",
                            isFavorite = true,
                            categoryId = 1
                        ),
                    )
                )
            }
        }
    }

    fun switchFavoriteStatus(productId: Int) {
        viewModelScope.launch {
            homeRepository.switchFavoriteStatus(productId)
        }
    }
}
