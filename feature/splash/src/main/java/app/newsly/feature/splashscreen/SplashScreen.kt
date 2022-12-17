package app.newsly.feature.splashscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.newsly.core.model.RequestException


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun SplashRoute(
    navigateToMain: () -> Unit,
    modifier: Modifier = Modifier,
    onErrorDetected: (RequestException) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SplashScreen(
        navigateToMain = navigateToMain,
        uiState = uiState,
        onErrorDetected = onErrorDetected
    )
}

@Composable
fun SplashScreen(
    navigateToMain: () -> Unit,
    uiState: SplashUiState,
    onErrorDetected: (RequestException) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.fillMaxSize(), text = "Splash")
        if (uiState is SplashUiState.Success) {
            navigateToMain()
        } else if (uiState is SplashUiState.Error) {
            onErrorDetected(uiState.exception)
        }
    }
}