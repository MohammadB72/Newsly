package app.newsly.feature.categories

import androidx.compose.runtime.Immutable
import app.newsly.core.domain.model.Category
import app.newsly.core.model.RequestException


@Immutable
sealed interface CategoriesUiState {
    object Loading : CategoriesUiState
    data class SUCCESS(val categories: List<Category>) : CategoriesUiState
    data class Failure(val exception: RequestException) : CategoriesUiState
}
