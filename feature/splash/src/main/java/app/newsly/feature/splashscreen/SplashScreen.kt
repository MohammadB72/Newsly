package app.newsly.feature.splashscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun SplashRoute(
    navigateToMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val uiState by viewModel.isGet.collectAsStateWithLifecycle()
    SplashScreen(navigateToMain = navigateToMain, uiState = uiState)
}

@Composable
fun SplashScreen(navigateToMain: () -> Unit, uiState: SplashUiState) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.fillMaxSize(), text = "Splash")
        val context = LocalContext.current
        LaunchedEffect(uiState) {
            if (uiState is SplashUiState.Success) {
                navigateToMain()
            } else if (uiState is SplashUiState.Error) {
                Toast.makeText(context, uiState.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}