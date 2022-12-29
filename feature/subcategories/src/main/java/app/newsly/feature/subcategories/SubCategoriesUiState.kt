package app.newsly.feature.subcategories

import androidx.compose.runtime.Immutable
import app.newsly.core.domain.model.Category
import app.newsly.core.model.RequestException

@Immutable
sealed interface SubCategoriesUiState {
    object Loading : SubCategoriesUiState
    data class Success(val subCategories: List<Category>) : SubCategoriesUiState
    data class Failure(val exception: RequestException) : SubCategoriesUiState
}