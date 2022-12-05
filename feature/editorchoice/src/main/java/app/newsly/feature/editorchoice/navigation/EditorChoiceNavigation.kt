package app.newsly.feature.editorchoice.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import app.newsly.feature.editorchoice.EditorChoiceRoute

const val editorChoiceNavigationRoute = "editor_choice_route"

fun NavController.navigateToEditorChoice(navOptions: NavOptions? = null) {
    this.navigate(editorChoiceNavigationRoute, navOptions)
}

fun NavGraphBuilder.editorChoiceScreen() {
    composable(editorChoiceNavigationRoute) {
        EditorChoiceRoute()
    }
}