package app.newsly.feature.categories.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import app.newsly.feature.categories.CategoriesRoute

const val categoriesNavigationRoute = "categories_route"

fun NavController.navigateToCategories(navOptions: NavOptions? = null) {
    this.navigate(categoriesNavigationRoute, navOptions)
}

fun NavGraphBuilder.categoriesScreen() {
    composable(categoriesNavigationRoute) {
        CategoriesRoute()
    }
}