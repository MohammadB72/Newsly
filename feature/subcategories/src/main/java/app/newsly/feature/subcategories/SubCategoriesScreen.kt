@file:OptIn(ExperimentalLifecycleComposeApi::class)

package app.newsly.feature.subcategories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.newsly.core.designsystem.component.PostCardSimple
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
    subCategoriesNewsUiState: Map<Int, SubCategoriesNewsUiState>,
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
    subCategoriesNewsUiState: Map<Int, SubCategoriesNewsUiState>,
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.navigationBarsPadding()
    ) {
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

        HorizontalPager(
            count = subcategories.size,
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            if (subCategoriesNewsUiState.isNotEmpty()) {
                when (val item = subCategoriesNewsUiState[page]) {
                    SubCategoriesNewsUiState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is SubCategoriesNewsUiState.Success -> {
                        NewsContent(subCategoriesNewsUiState = item)
                    }
                    is SubCategoriesNewsUiState.Failure -> {
                        FailureContent(exception = item.exception) {

                        }
                    }
                    null -> {

                    }
                }
            }
        }
    }
}

@Composable
fun NewsContent(
    modifier: Modifier = Modifier,
    subCategoriesNewsUiState: SubCategoriesNewsUiState.Success,
    onPostTapped: (postId: Int) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState()
    ) {
        items(
            items = subCategoriesNewsUiState.newsList,
            key = { post -> post.id }
        ) { post ->
            PostCardSimple(
                id = post.id,
                title = post.title,
                imageUrl = post.image,
                authorName = post.author.name,
                authorAvatar = post.author.avatar,
                postDate = post.date,
                onPostTapped = onPostTapped,
            )
            PostListDivider()
        }
    }
}

@Composable
private fun PostListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
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