package app.newsly.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import app.newsly.feature.apps.navigation.appsNavigationRoute
import app.newsly.feature.apps.navigation.navigateToApps
import app.newsly.feature.categories.navigation.categoriesNavigationRoute
import app.newsly.feature.categories.navigation.navigateToCategories
import app.newsly.feature.main.navigation.TopLevelDestination
import app.newsly.feature.news.navigation.navigateToNews
import app.newsly.feature.news.navigation.newsNavigationRoute


@Composable
fun rememberMainScreenState(
    navController: NavHostController = rememberNavController()
): MainScreenState {
    return remember {
        MainScreenState(navController)
    }
}

class MainScreenState(
    val navController: NavHostController
) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()


    val currentDestination: TopLevelDestination?
        @Composable get() = when (navController.currentBackStackEntryAsState().value?.destination?.route) {
            newsNavigationRoute -> TopLevelDestination.NEWS
            categoriesNavigationRoute -> TopLevelDestination.CATEGORIES
            appsNavigationRoute -> TopLevelDestination.Apps
            else -> null
        }


    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {

        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.NEWS -> {
                navController.navigateToNews(topLevelNavOptions)
            }
            TopLevelDestination.CATEGORIES -> {
                navController.navigateToCategories(topLevelNavOptions)
            }
            TopLevelDestination.Apps -> {
                navController.navigateToApps(topLevelNavOptions)
            }
        }

    }
}