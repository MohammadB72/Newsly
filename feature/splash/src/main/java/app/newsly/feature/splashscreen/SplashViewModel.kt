package app.newsly.feature.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.newsly.core.domain.GetServerStatusUseCase
import app.newsly.core.model.RequestException
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


private data class HomeViewModelState(
    val isLoading: Boolean = false,
    val isUp: Boolean = false,
    val exception: RequestException? = null
) {
    fun toUiState(): SplashUiState {
        return if (!isLoading && isUp) {
            SplashUiState.Success(isUp = true)
        } else if (!isLoading && exception != null) {
            SplashUiState.Failure(exception = exception)
        } else {
            SplashUiState.Loading
        }
    }
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    val getServerStatusUseCase: GetServerStatusUseCase,
) : ViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))
    val uiState = viewModelState
        .map {
            it.toUiState()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        getServerStatus()
    }

    private fun getServerStatus() {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getServerStatusUseCase()
                .doOnSuccess { serverStatus ->
                    viewModelState.update {
                        it.copy(isLoading = false, isUp = serverStatus.up)
                    }
                }.doOnFailure { exception ->
                    viewModelState.update {
                        it.copy(
                            isLoading = false,
                            exception = exception.copy(retryBlock = { getServerStatus() })
                        )
                    }
                }
        }
    }
}