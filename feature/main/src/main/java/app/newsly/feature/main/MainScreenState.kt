package app.newsly.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import app.newsly.feature.main.navigation.TopLevelDestination

@Composable
fun rememberMainScreenState(): MainScreenState {
    return remember {
        MainScreenState()
    }
}

class MainScreenState(

) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()
}