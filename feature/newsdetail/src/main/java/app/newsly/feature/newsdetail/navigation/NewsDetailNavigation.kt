package app.newsly.feature.newsdetail.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.newsly.core.model.RequestException
import app.newsly.feature.newsdetail.NewsDetailRoute


internal const val postIdArgs = "topicId"
const val newsDetailNavigationRoute = "news_detail_route?postId={$postIdArgs}"

fun NavController.navigateToNewsDetail(postId: Int, navOptions: NavOptions? = null) {
    this.navigate("news_detail_route?postId=$postId", navOptions)
}

fun NavGraphBuilder.newsDetailScreen(
    onFailureOccurred: @Composable (RequestException) -> Unit,
) {
    composable(
        route = newsDetailNavigationRoute,
        arguments = listOf(navArgument(postIdArgs) {
            type = NavType.StringType
            defaultValue = ""
        })
    ) {
        NewsDetailRoute(onFailureOccurred = onFailureOccurred)
    }
}