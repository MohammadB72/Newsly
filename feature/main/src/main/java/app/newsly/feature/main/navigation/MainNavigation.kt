package app.newsly.feature.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.newsly.feature.main.MainRoute


private const val mainGraphRoutePattern = "main_graph"
const val mainRoute = "main_route"


fun NavController.navigateToMainGraph(navOptions: NavOptions? = null) {
    this.navigate(mainGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.mainGraph(onPostTapped: (postId: Int) -> Unit) {
    navigation(
        startDestination = mainRoute,
        route = mainGraphRoutePattern,
    )
    {
        composable(route = mainRoute) {
            MainRoute(onPostTapped = onPostTapped)
        }
    }
}