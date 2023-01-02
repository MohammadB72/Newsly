package app.newsly.feature.subcategories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.newsly.core.domain.GetNewsByCategoryUseCase
import app.newsly.core.domain.GetSubCategoriesUseCase
import app.newsly.core.model.RequestResult
import app.newsly.feature.subcategories.navigation.categoryIdArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubCategoriesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSubCategoriesUseCase: GetSubCategoriesUseCase,
    private val getNewsByCategoryUseCase: GetNewsByCategoryUseCase,
) : ViewModel() {

    private val categoryId = savedStateHandle.get<Int>(categoryIdArgs)

    private val _uiState = MutableStateFlow<SubCategoriesUiState>(SubCategoriesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _subCategoriesNewsUiState =
        MutableStateFlow<SubCategoriesNewsUiState>(SubCategoriesNewsUiState.Loading)
    val subCategoriesNewsUiState = _subCategoriesNewsUiState.asStateFlow()

    init {
        categoryId?.let { getSubCategories(it) }
    }

    private fun getSubCategories(categoryId: Int) {
        viewModelScope.launch {
            getSubCategoriesUseCase.invoke(categoryId = categoryId).collect { result ->
                _uiState.update {
                    when (result) {
                        is RequestResult.Loading -> {
                            SubCategoriesUiState.Loading
                        }
                        is RequestResult.Success -> {
                            result.data.forEach {
                                getNewsByCategory(it.id)
                            }
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

    private fun getNewsByCategory(categoryId: Int) {
        viewModelScope.launch {
            getNewsByCategoryUseCase.invoke(categoryId = categoryId, page = 1).collect { result ->
                _subCategoriesNewsUiState.update {
                    when (result) {
                        is RequestResult.Loading -> {
                            SubCategoriesNewsUiState.Loading
                        }
                        is RequestResult.Success -> {
                            SubCategoriesNewsUiState.Success(
                                categoryId = categoryId,
                                newsList = result.data
                            )
                        }
                        is RequestResult.Fail -> {
                            SubCategoriesNewsUiState.Failure(result.exception)
                        }
                    }
                }
            }
        }
    }
}