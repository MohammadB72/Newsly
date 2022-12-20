package app.newsly.feature.splashscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.newsly.core.model.RequestException
import app.newsly.shared.resources.R


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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displaySmall
        )
        when (uiState) {
            SplashUiState.Loading -> {
                LoadingContent()
            }
            is SplashUiState.Success -> {
                navigateToMain()
            }
            is SplashUiState.Failure -> {
                FailureContent(
                    exception = uiState.exception,
                    onFailureOccurred = onFailureOccurred
                )
            }
        }
    }
}

@Composable
fun LoadingContent() {
    CircularProgressIndicator()
}

@Composable
fun FailureContent(
    exception: RequestException,
    onFailureOccurred: @Composable (RequestException) -> Unit
) {
    Box {
        onFailureOccurred(exception)
        Button(onClick = { exception.retryBlock() }) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}