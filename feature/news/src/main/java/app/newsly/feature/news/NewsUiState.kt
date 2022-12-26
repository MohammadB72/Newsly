package app.newsly.feature.news

import androidx.compose.runtime.Immutable
import app.newsly.core.domain.model.News
import app.newsly.core.model.RequestException


@Immutable
sealed interface NewsUiState {
    object Loading : NewsUiState
    data class SUCCESS(val news: List<News>) : NewsUiState
    data class Failure(val exception: RequestException) : NewsUiState
}
