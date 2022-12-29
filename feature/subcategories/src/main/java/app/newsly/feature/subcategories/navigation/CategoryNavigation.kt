package app.newsly.feature.subcategories.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.newsly.core.model.RequestException
import app.newsly.feature.subcategories.SubCategoriesRoute

internal const val categoryIdArgs = "categoryId"
const val subCategoriesNavigationRoute =
    "sub_categories_route?$categoryIdArgs={${categoryIdArgs}}"

fun NavController.navigateToSubCategories(categoryId: Int, navOptions: NavOptions? = null) {
    val address = subCategoriesNavigationRoute.replace("{$categoryIdArgs}", "$categoryId")
    this.navigate(address, navOptions)
}

fun NavGraphBuilder.subCategoriesScreen(
    onFailureOccurred: @Composable (RequestException) -> Unit,
) {
    composable(
        route = subCategoriesNavigationRoute,
        arguments = listOf(navArgument(categoryIdArgs) {
            type = NavType.IntType
            defaultValue = 0
        })
    ) {
        SubCategoriesRoute(onFailureOccurred = onFailureOccurred)
    }
}