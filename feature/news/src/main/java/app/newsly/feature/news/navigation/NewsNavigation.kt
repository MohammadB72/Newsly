package app.newsly.feature.news.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import app.newsly.feature.news.NewsRoute

const val newsNavigationRoute = "news_route"

fun NavController.navigateToNews(navOptions: NavOptions? = null) {
    this.navigate(newsNavigationRoute, navOptions)
}

fun NavGraphBuilder.newsScreen() {
    composable(newsNavigationRoute) {
        NewsRoute()
    }
}