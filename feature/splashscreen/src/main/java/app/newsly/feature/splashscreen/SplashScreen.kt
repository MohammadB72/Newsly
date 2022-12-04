package app.newsly.feature.splashscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay


@Composable
internal fun SplashRoute(navigateToMain: () -> Unit) {
    SplashScreen(navigateToMain = navigateToMain)
}

@Composable
fun SplashScreen(navigateToMain: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.fillMaxSize(), text = "Splash")
        LaunchedEffect(true) {
            delay(2000)
            navigateToMain()
        }
    }
}