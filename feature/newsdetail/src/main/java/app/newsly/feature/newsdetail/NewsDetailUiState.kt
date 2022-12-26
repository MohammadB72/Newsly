package app.newsly.feature.newsdetail

import androidx.compose.runtime.Immutable
import app.newsly.core.domain.model.NewsDetail
import app.newsly.core.model.RequestException

@Immutable
sealed interface NewsDetailUiState {
    object Loading : NewsDetailUiState
    data class Success(val newsDetail: NewsDetail) : NewsDetailUiState
    data class Failure(val exception: RequestException) : NewsDetailUiState
}