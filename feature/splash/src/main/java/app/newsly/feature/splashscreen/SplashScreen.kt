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
    onFailureOccurred: @Composable (RequestException) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    SplashScreen(
        navigateToMain = navigateToMain,
        uiState = uiState,
        onFailureOccurred = onFailureOccurred
    )
}

@Composable
fun SplashScreen(
    navigateToMain: () -> Unit,
    uiState: SplashUiState,
    onFailureOccurred: @Composable (RequestException) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        when (uiState) {
            SplashUiState.Loading -> {
                Text(modifier = Modifier.fillMaxSize(), text = "Splash Loading")
            }
            is SplashUiState.Success -> {
                navigateToMain()
            }
            is SplashUiState.Failure -> {
                Text(modifier = Modifier.fillMaxSize(), text = "Splash Fail")
                onFailureOccurred(uiState.exception)
            }
            else -> {}
        }
    }
}