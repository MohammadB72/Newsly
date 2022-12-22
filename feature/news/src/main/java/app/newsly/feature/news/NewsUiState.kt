package app.newsly.feature.news

import androidx.compose.runtime.Immutable
import app.newsly.core.model.RequestException
import app.newsly.core.model.domain.News


@Immutable
sealed interface NewsUiState {
    object Loading : NewsUiState
    data class HasNews(val news: List<News>) : NewsUiState
    data class Failure(val exception: RequestException) : NewsUiState
}
