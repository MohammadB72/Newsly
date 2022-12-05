package app.newsly.feature.main

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.newsly.feature.main.navigation.NewslyNavHost
import app.newsly.feature.main.navigation.TopLevelDestination

@Composable
fun MainRoute(
    mainScreenState: MainScreenState = rememberMainScreenState()
) {
    MainScreen(
        mainScreenState = mainScreenState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainScreenState: MainScreenState
) {
    Scaffold(
        modifier = Modifier,
        topBar = {},
        bottomBar = {
            NewslyBottomBar(
                destinations = mainScreenState.topLevelDestinations,
                currentDestination = mainScreenState.currentDestination,
                onNavigateToDestination = mainScreenState::navigateToTopLevelDestination
            )
        }
    ) { padding ->
        padding
        NewslyNavHost(navController = mainScreenState.navController)
    }
}

@Composable
private fun NewslyBottomBar(
    destinations: List<TopLevelDestination>,
    currentDestination: TopLevelDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit
) {
    NavigationBar {
        destinations.forEach { destination ->
            val selected = true
            NavigationBarItem(
                selected = currentDestination == destination,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    if (selected) destination.selectedIcon.ToIcon() else destination.unselectedIcon.ToIcon()
                },
                label = { Text(text = stringResource(id = destination.titleTextId)) },
            )
        }
    }
}