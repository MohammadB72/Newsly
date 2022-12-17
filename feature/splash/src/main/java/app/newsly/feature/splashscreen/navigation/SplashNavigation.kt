package app.newsly.feature.splashscreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import app.newsly.core.model.RequestException
import app.newsly.feature.splashscreen.SplashRoute

const val splashNavigationRoute = "splash_route"

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    this.navigate(splashNavigationRoute, navOptions)
}

fun NavGraphBuilder.splashScreen(
    navigateToMain: () -> Unit,
    onFailureOccured: @Composable (RequestException) -> Unit
) {
    composable(splashNavigationRoute) {
        SplashRoute(navigateToMain = navigateToMain, onFailureOccured = onFailureOccured)
    }
}