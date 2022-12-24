package app.newsly.feature.newsdetail.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import app.newsly.feature.newsdetail.NewsDetailRoute


const val newsDetailNavigationRoute = "news_detail_route?postId={postId}"

fun NavController.navigateToNewsDetail(postId: Int, navOptions: NavOptions? = null) {
    this.navigate("news_detail_route?postId=$postId", navOptions)
}

fun NavGraphBuilder.newsDetailScreen() {
    composable(
        route = newsDetailNavigationRoute,
        arguments = listOf(navArgument("postId") {
            type = NavType.IntType
            defaultValue = -1
        })
    ) { backStackEntry ->
        val postId = backStackEntry.arguments?.getInt("postId")
        NewsDetailRoute(postId = postId!!)
    }
}