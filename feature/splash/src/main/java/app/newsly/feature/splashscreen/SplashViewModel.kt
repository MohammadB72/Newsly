package app.newsly.feature.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.newsly.core.domain.GetServerStatusUseCase
import app.newsly.core.model.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getServerStatusUseCase: GetServerStatusUseCase,
) : ViewModel() {

    private val _splashUiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val splashUiState = _splashUiState.asStateFlow()

    init {
        getServerStatus()
    }

    private fun getServerStatus() {
        viewModelScope.launch {
            getServerStatusUseCase.invoke()
                .collect { result ->
                    _splashUiState.value =
                        when (result) {
                            is RequestResult.Loading -> {
                                SplashUiState.Loading
                            }
                            is RequestResult.Success -> {
                                SplashUiState.ServerStatusIsUp
                            }
                            is RequestResult.Fail -> {
                                SplashUiState.Failure(result.exception.copy(retryBlock = { getServerStatus() }))
                            }
                        }
                }
        }
    }
}