package app.newsly.feature.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.newsly.core.domain.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {
    init {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect()
        }
    }
}