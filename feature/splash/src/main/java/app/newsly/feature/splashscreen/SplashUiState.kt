package app.newsly.feature.splashscreen

import androidx.compose.runtime.Immutable
import app.newsly.core.model.RequestException

@Immutable
sealed interface SplashUiState {
    object Loading : SplashUiState
    data class Success(val isUp: Boolean) : SplashUiState
    data class Failure(val exception: RequestException) : SplashUiState
}