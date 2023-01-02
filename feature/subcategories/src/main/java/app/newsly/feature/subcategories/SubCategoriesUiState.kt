package app.newsly.feature.subcategories

import androidx.compose.runtime.Immutable
import app.newsly.core.domain.model.Category
import app.newsly.core.domain.model.News
import app.newsly.core.model.RequestException


@Immutable
sealed interface SubCategoriesNewsUiState {
    object Loading : SubCategoriesNewsUiState
    data class Success(val categoryId: Int, val newsList: List<News>) :
        SubCategoriesNewsUiState

    data class Failure(val exception: RequestException) : SubCategoriesNewsUiState
}

@Immutable
sealed interface SubCategoriesUiState {
    object Loading : SubCategoriesUiState
    data class Success(val subCategories: List<Category>) : SubCategoriesUiState
    data class Failure(val exception: RequestException) : SubCategoriesUiState
}