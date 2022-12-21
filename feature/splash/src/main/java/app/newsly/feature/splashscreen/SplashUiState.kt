package app.newsly.feature.splashscreen

import androidx.compose.runtime.Immutable
import app.newsly.core.model.RequestException

@Immutable
sealed interface SplashUiState {
    object Loading : SplashUiState
    object ServerStatusIsUp : SplashUiState
    data class Failure(val exception: RequestException) : SplashUiState
}