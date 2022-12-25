package app.newsly.feature.newsdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.newsly.core.domain.GetNewsDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val getNewsDetailUseCase: GetNewsDetailUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            getNewsDetailUseCase.invoke().collect {

            }
        }
    }
}