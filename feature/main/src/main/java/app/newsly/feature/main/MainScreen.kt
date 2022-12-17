package app.newsly.feature.main

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.newsly.feature.main.navigation.NewslyNavHost
import app.newsly.feature.main.navigation.TopLevelDestination
import app.newsly.shared.resources.R

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
        topBar = { TopAppBar() },
        bottomBar = {
            BottomBar(
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
private fun BottomBar(
    destinations: List<TopLevelDestination>,
    currentDestination: TopLevelDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit
) {
    NavigationBar {
        destinations.forEach { destination ->
            val selected = currentDestination == destination
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.enterAlwaysScrollBehavior(
        topAppBarState
    )
) {
    val title = stringResource(id = R.string.app_name)
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}