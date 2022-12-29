package app.newsly.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.newsly.core.model.RequestException
import app.newsly.feature.main.MainRoute


private const val mainGraphRoutePattern = "main_graph"
const val mainRoute = "main_route"


fun NavController.navigateToMainGraph(navOptions: NavOptions? = null) {
    this.navigate(mainGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.mainGraph(
    onPostTapped: (postId: Int) -> Unit,
    onCategoryTapped: (categoryId: Int) -> Unit,
    onFailureOccurred: @Composable (RequestException) -> Unit
) {
    navigation(
        startDestination = mainRoute,
        route = mainGraphRoutePattern,
    )
    {
        composable(route = mainRoute) {
            MainRoute(
                onPostTapped = onPostTapped,
                onCategoryTapped = onCategoryTapped,
                onFailureOccurred = onFailureOccurred
            )
        }
    }
}