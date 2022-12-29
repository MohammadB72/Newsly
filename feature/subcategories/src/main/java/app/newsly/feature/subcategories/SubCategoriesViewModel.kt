package app.newsly.feature.subcategories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.newsly.core.domain.GetSubCategoriesUseCase
import app.newsly.core.model.RequestResult
import app.newsly.feature.subcategories.navigation.categoryIdArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubCategoriesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSubCategoriesUseCase: GetSubCategoriesUseCase,
) : ViewModel() {

    private val categoryId = savedStateHandle.get<Int>(categoryIdArgs)

    private val _uiState = MutableStateFlow<SubCategoriesUiState>(SubCategoriesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        categoryId?.let { getSubCategories(it) }
    }

    private fun getSubCategories(categoryId: Int) {
        viewModelScope.launch {
            getSubCategoriesUseCase.invoke(categoryId = categoryId).collect() { result ->
                _uiState.value =
                    when (result) {
                        is RequestResult.Loading -> {
                            SubCategoriesUiState.Loading
                        }
                        is RequestResult.Success -> {
                            SubCategoriesUiState.Success(result.data)
                        }
                        is RequestResult.Fail -> {
                            SubCategoriesUiState.Failure(result.exception.copy(retryBlock = {
                                getSubCategories(
                                    categoryId
                                )
                            }))
                        }
                    }
            }
        }
    }
}