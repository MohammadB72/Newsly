@file:OptIn(ExperimentalLifecycleComposeApi::class)

package app.newsly.feature.subcategories

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.newsly.core.domain.model.Category
import app.newsly.core.model.RequestException
import app.newsly.shared.resources.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalLifecycleComposeApi
@Composable
fun SubCategoriesRoute(
    onFailureOccurred: @Composable (RequestException) -> Unit,
    viewModel: SubCategoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val subCategoriesNewsUiState by viewModel.subCategoriesNewsUiState.collectAsStateWithLifecycle()

    SubCategoriesScreen(
        uiState = uiState,
        subCategoriesNewsUiState = subCategoriesNewsUiState,
        onFailureOccurred = onFailureOccurred
    )
}

@Composable
fun SubCategoriesScreen(
    uiState: SubCategoriesUiState,
    subCategoriesNewsUiState: SubCategoriesNewsUiState,
    onFailureOccurred: @Composable (RequestException) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        when (uiState) {
            SubCategoriesUiState.Loading -> {
                LoadingContent()
            }
            is SubCategoriesUiState.Success -> {
                SuccessContent(
                    subcategories = uiState.subCategories,
                    subCategoriesNewsUiState = subCategoriesNewsUiState
                )
            }
            is SubCategoriesUiState.Failure -> {
                FailureContent(exception = uiState.exception, onFailureOccurred = onFailureOccurred)
            }
        }
    }
}

@Composable
fun LoadingContent() {
    CircularProgressIndicator()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SuccessContent(
    subcategories: List<Category>,
    subCategoriesNewsUiState: SubCategoriesNewsUiState,
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colorScheme.surface,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .statusBarsPadding()
        ) {
            subcategories.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(text = title.title, color = MaterialTheme.colorScheme.onSurface)
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(count = subcategories.size, state = pagerState) { page ->

            when (subCategoriesNewsUiState) {
                SubCategoriesNewsUiState.Loading -> {

                }
                is SubCategoriesNewsUiState.Success -> {
                    NewsContent(subCategoriesNewsUiState = subCategoriesNewsUiState)
                }
                is SubCategoriesNewsUiState.Failure -> {

                }
            }

        }
    }
}


@Composable
fun FailureContent(
    exception: RequestException,
    onFailureOccurred: @Composable (RequestException) -> Unit,
) {
    onFailureOccurred(exception)
    Button(onClick = { exception.retryBlock() }) {
        Text(text = stringResource(id = R.string.retry))
    }
}