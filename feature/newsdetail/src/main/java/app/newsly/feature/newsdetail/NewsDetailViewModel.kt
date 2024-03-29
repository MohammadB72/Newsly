package app.newsly.feature.newsdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.newsly.core.domain.GetNewsDetailUseCase
import app.newsly.core.model.RequestResult
import app.newsly.feature.newsdetail.navigation.postIdArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getNewsDetailUseCase: GetNewsDetailUseCase,
) : ViewModel() {

    private val postId = savedStateHandle.get<Int>(postIdArgs)

    private val _uiState = MutableStateFlow<NewsDetailUiState>(NewsDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        postId?.let { getNewsDetail(it) }
    }

    private fun getNewsDetail(postId: Int) {
        viewModelScope.launch {
            getNewsDetailUseCase.invoke(postId = postId).collect { result ->
                _uiState.value =
                    when (result) {
                        is RequestResult.Loading -> {
                            NewsDetailUiState.Loading
                        }
                        is RequestResult.Success -> {
                            NewsDetailUiState.Success(result.data)
                        }
                        is RequestResult.Fail -> {
                            NewsDetailUiState.Failure(result.exception.copy(retryBlock = {
                                getNewsDetail(
                                    postId
                                )
                            }))
                        }
                    }
            }
        }
    }
}