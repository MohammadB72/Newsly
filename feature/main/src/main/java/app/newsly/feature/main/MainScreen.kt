package app.newsly.feature.main

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import app.newsly.feature.main.navigation.TopLevelDestination

@Composable
fun MainRoute(
    navigateToNews: () -> Unit,
    navigateToEditorChoice: () -> Unit,
    navigateToCategories: () -> Unit,
    navigateToApps: () -> Unit
) {
    MainScreen(
        uiState = rememberMainScreenState(),
        navigateToNews = navigateToNews,
        navigateToEditorChoice = navigateToEditorChoice,
        navigateToCategories = navigateToCategories,
        navigateToApps = navigateToApps
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainScreenState,
    navigateToNews: () -> Unit,
    navigateToEditorChoice: () -> Unit,
    navigateToCategories: () -> Unit,
    navigateToApps: () -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {},
        bottomBar = {
            NewslyBottomBar(
                destinations = uiState.topLevelDestinations,
                onNavigateToDestination = { destenation ->
                    when (destenation) {
                        TopLevelDestination.NEWS -> {
                            navigateToNews()
                        }
                        TopLevelDestination.EDITOR_CHOICE -> {
                            navigateToEditorChoice()
                        }
                        TopLevelDestination.CATEGORIES -> {
                            navigateToCategories()
                        }
                        TopLevelDestination.Apps -> {
                            navigateToApps()
                        }
                    }
                }
            )
        }
    ) { padding ->

    }
}

@Composable
private fun NewslyBottomBar(
    destinations: List<TopLevelDestination>,
    //currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit
) {
    NavigationBar {
        destinations.forEach { destination ->
            val selected = true
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    if (selected) destination.selectedIcon.ToIcon() else destination.unselectedIcon.ToIcon()
                },
                label = { Text(text = stringResource(id = destination.titleTextId)) },
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false