package app.newsly.feature.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.newsly.core.domain.GetCategoriesUseCase
import app.newsly.core.model.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _categoriesUiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Loading)
    val categoriesUiState = _categoriesUiState.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { result ->
                _categoriesUiState.value =
                    when (result) {
                        RequestResult.Loading -> {
                            CategoriesUiState.Loading
                        }
                        is RequestResult.Success -> {
                            CategoriesUiState.SUCCESS(result.data)
                        }
                        is RequestResult.Fail -> {
                            CategoriesUiState.Failure(result.exception)
                        }
                    }

            }
        }
    }
}