package app.newsly.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import app.newsly.core.model.RequestException
import app.newsly.feature.main.navigation.mainGraph
import app.newsly.feature.main.navigation.navigateToMainGraph
import app.newsly.feature.splashscreen.navigation.splashNavigationRoute
import app.newsly.feature.splashscreen.navigation.splashScreen


@Composable
fun NewslyNavgraph(onFailureOccurred: @Composable (RequestException) -> Unit) {
    val navController = rememberNavController()
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
            onFailureOccurred = onFailureOccurred
        )
        mainGraph()
    }
}