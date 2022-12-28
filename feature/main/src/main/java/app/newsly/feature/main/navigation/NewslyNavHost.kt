package app.newsly.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import app.newsly.core.model.RequestException
import app.newsly.feature.apps.navigation.appsScreen
import app.newsly.feature.categories.navigation.categoriesScreen
import app.newsly.feature.news.navigation.newsNavigationRoute
import app.newsly.feature.news.navigation.newsScreen


@Composable
fun NewslyNavHost(
    modifier: Modifier = Modifier,
    onPostTapped: (postId: Int) -> Unit,
    onFailureOccurred: @Composable (RequestException) -> Unit,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = newsNavigationRoute
    ) {
        newsScreen(
            onPostTapped = onPostTapped,
            onFailureOccurred = onFailureOccurred
        )
        categoriesScreen()
        appsScreen()

    }
}