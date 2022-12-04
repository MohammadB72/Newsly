package app.newsly.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import app.newsly.feature.main.navigation.mainGraph
import app.newsly.feature.main.navigation.navigateToMainGraph
import app.newsly.feature.splashscreen.navigation.splashNavigationRoute
import app.newsly.feature.splashscreen.navigation.splashScreen

@Composable
fun NewslyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = splashNavigationRoute
    ) {
        splashScreen(navigateToMain = {
            navController.navigateToMainGraph(navOptions {
                popUpTo(splashNavigationRoute) { inclusive = true }
            })
        })
        mainGraph(
            navigateToNews = {},
            navigateToEditorChoice = {},
            navigateToCategories = {},
            navigateToApps = {},
        )
    }
}