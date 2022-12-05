package app.newsly.feature.apps.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import app.newsly.feature.apps.AppsRoute

const val appsNavigationRoute = "apps_route"

fun NavController.navigateToApps(navOptions: NavOptions? = null) {
    this.navigate(appsNavigationRoute, navOptions)
}

fun NavGraphBuilder.appsScreen() {
    composable(appsNavigationRoute) {
        AppsRoute()
    }
}