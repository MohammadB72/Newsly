package app.newsly.feature.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.newsly.core.domain.GetServerStatusUseCase
import app.newsly.core.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


sealed interface SplashUiState {
    object Loading : SplashUiState
    data class Success(val isUp: Boolean) : SplashUiState
    data class Error(val exception: Exception) : SplashUiState
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    getServerStatus: GetServerStatusUseCase,
) : ViewModel() {

    val isGet: StateFlow<SplashUiState> = getServerStatus().map { result ->
        when (result) {
            is Result.Success -> {
                SplashUiState.Success(result.data.up)
            }
            is Result.Error -> {
                SplashUiState.Error(result.exception)
            }
            Result.Loading -> TODO()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SplashUiState.Loading
    )
}