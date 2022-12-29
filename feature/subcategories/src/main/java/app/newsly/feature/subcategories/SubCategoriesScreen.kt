@file:OptIn(ExperimentalLifecycleComposeApi::class)

package app.newsly.feature.subcategories

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.newsly.core.model.RequestException

@Composable
fun SubCategoriesRoute(
    onFailureOccurred: @Composable (RequestException) -> Unit,
    viewModel: SubCategoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SubCategoriesScreen(uiState = uiState, onFailureOccurred = onFailureOccurred)
}

@Composable
fun SubCategoriesScreen(
    uiState: SubCategoriesUiState,
    onFailureOccurred: @Composable (RequestException) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        when (uiState) {
            SubCategoriesUiState.Loading -> {}
            is SubCategoriesUiState.Success -> {
                uiState.subCategories.forEach {
                    Log.w("sdfsdfsdfsdf", it.title)
                }
            }
            is SubCategoriesUiState.Failure -> {

            }
        }
    }
}