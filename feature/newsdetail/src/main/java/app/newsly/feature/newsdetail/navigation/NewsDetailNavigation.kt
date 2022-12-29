package app.newsly.feature.newsdetail.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.newsly.core.model.RequestException
import app.newsly.feature.newsdetail.NewsDetailRoute


internal const val postIdArgs = "postId"
const val newsDetailNavigationRoute = "news_detail_route?$postIdArgs={$postIdArgs}"

fun NavController.navigateToNewsDetail(postId: Int, navOptions: NavOptions? = null) {
    val address = newsDetailNavigationRoute.replace("{$postIdArgs}", "$postId")
    this.navigate(address, navOptions)
}

fun NavGraphBuilder.newsDetailScreen(
    onFailureOccurred: @Composable (RequestException) -> Unit,
) {
    composable(
        route = newsDetailNavigationRoute,
        arguments = listOf(navArgument(postIdArgs) {
            type = NavType.IntType
            defaultValue = 0
        })
    ) {
        NewsDetailRoute(onFailureOccurred = onFailureOccurred)
    }
}