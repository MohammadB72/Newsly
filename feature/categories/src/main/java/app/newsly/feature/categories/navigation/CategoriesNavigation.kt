package app.newsly.feature.categories.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import app.newsly.core.model.RequestException
import app.newsly.feature.categories.CategoriesRoute

const val categoriesNavigationRoute = "categories_route"

fun NavController.navigateToCategories(navOptions: NavOptions? = null) {
    this.navigate(categoriesNavigationRoute, navOptions)
}

fun NavGraphBuilder.categoriesScreen(
    onCategoryTapped: (categoryId: Int) -> Unit,
    onFailureOccurred: @Composable (RequestException) -> Unit,
) {
    composable(categoriesNavigationRoute) {
        CategoriesRoute(onCategoryTapped = onCategoryTapped, onFailureOccurred = onFailureOccurred)
    }
}