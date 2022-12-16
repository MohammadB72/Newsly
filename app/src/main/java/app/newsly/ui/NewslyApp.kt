package app.newsly.ui

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import app.newsly.core.model.ErrorBehaviour
import app.newsly.feature.main.navigation.mainGraph
import app.newsly.feature.main.navigation.navigateToMainGraph
import app.newsly.feature.splashscreen.navigation.splashNavigationRoute
import app.newsly.feature.splashscreen.navigation.splashScreen
import kotlinx.coroutines.launch

@Composable
fun NewslyApp() {
    val navController = rememberNavController()

    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier)

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = splashNavigationRoute
    ) {
        splashScreen(
            navigateToMain = {
                navController.navigateToMainGraph(navOptions {
                    popUpTo(splashNavigationRoute) { inclusive = true }
                })
            },
            onErrorDetected = { exception ->
                if (exception.errorBehaviour == ErrorBehaviour.SHOW_SNACK) {
                    snackScope.launch {
                        val snackbarResult = snackState.showSnackbar(
                            exception.message.toString(),
                            actionLabel = "Retry"
                        )
                        if (snackbarResult == SnackbarResult.ActionPerformed) {
                            exception.retry()
                        }
                    }
                }
            })
        mainGraph()
    }
}