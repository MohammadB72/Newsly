package app.newsly.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.newsly.core.model.FailureAction
import app.newsly.core.model.RequestException
import app.newsly.feature.main.navigation.NewslyNavHost
import app.newsly.feature.main.navigation.TopLevelDestination
import app.newsly.shared.resources.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainRoute(
    onPostTapped: (postId: Int) -> Unit,
    onCategoryTapped: (categoryId: Int) -> Unit,
    onFailureOccurred: @Composable (RequestException) -> Unit,
    mainScreenState: MainScreenState = rememberMainScreenState()
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { BottomSheet() },
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        MainScreen(
            mainScreenState = mainScreenState,
            sheetState = sheetState,
            coroutineScope = coroutineScope,
            onPostTapped = onPostTapped,
            onCategoryTapped = onCategoryTapped,
            onFailureOccurred = onFailureOccurred
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    mainScreenState: MainScreenState,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    onPostTapped: (postId: Int) -> Unit,
    onCategoryTapped: (categoryId: Int) -> Unit,
    onFailureOccurred: @Composable (RequestException) -> Unit,
) {
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                sheetState = sheetState,
                coroutineScope = coroutineScope
            )
        },
        bottomBar = {
            BottomBar(
                destinations = mainScreenState.topLevelDestinations,
                currentDestination = mainScreenState.currentDestination,
                onNavigateToDestination = mainScreenState::navigateToTopLevelDestination
            )
        }
    ) { padding ->
        val contentModifier = Modifier
            .padding(padding)
        val context = LocalContext.current
        NewslyNavHost(
            modifier = contentModifier,
            onPostTapped = onPostTapped,
            onCategoryTapped = onCategoryTapped,
            onFailureOccurred = { exception ->
                LaunchedEffect(snackbarHostState, exception.id)
                {
                    if (exception.actionAfterFailure == FailureAction.NONE) {
                        val snackbarResult = snackbarHostState.showSnackbar(
                            message = exception.networkErrorMessage.toString(),
                            actionLabel = context.getString(R.string.retry)
                        )
                        if (snackbarResult == SnackbarResult.ActionPerformed) {
                            exception.retryBlock()
                        }
                    }
                }
            },
            navController = mainScreenState.navController
        )


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
                label = {
                    Text(
                        text = stringResource(id = destination.titleTextId),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.enterAlwaysScrollBehavior(
        topAppBarState
    )
) {
    val title = stringResource(id = R.string.app_name)
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                })
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@Composable
fun BottomSheet() {
    Column(
        modifier = Modifier.padding(32.dp)
    ) {
        Text(
            text = "Bottom sheet",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Click outside the bottom sheet to hide it",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}