package app.newsly.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.newsly.core.domain.GetNewsUseCase
import app.newsly.core.model.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    private val _newsUiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val newsUiState = _newsUiState.asStateFlow()

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            getNewsUseCase().collect { result ->
                _newsUiState.value =
                    when (result) {
                        RequestResult.Loading -> {
                            NewsUiState.Loading
                        }
                        is RequestResult.Success -> {
                            NewsUiState.SUCCESS(result.data)
                        }
                        is RequestResult.Fail -> {
                            NewsUiState.Failure(result.exception)
                        }
                    }

            }
        }
    }
}