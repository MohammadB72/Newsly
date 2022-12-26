package app.newsly.feature.news.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import app.newsly.core.model.RequestException
import app.newsly.feature.news.NewsRoute

const val newsNavigationRoute = "news_route"

fun NavController.navigateToNews(navOptions: NavOptions? = null) {
    this.navigate(newsNavigationRoute, navOptions)
}

fun NavGraphBuilder.newsScreen(
    onPostTapped: (postId: Int) -> Unit,
    onFailureOccurred: @Composable (RequestException) -> Unit
) {
    composable(newsNavigationRoute) {
        NewsRoute(
            onPostTapped = onPostTapped,
            onFailureOccurred = onFailureOccurred
        )
    }
}