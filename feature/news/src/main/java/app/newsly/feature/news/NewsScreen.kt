package app.newsly.feature.news

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.newsly.core.designsystem.component.PostCardSimple
import app.newsly.core.model.domain.News

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun NewsRoute(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val uiState by viewModel.newsUiState.collectAsStateWithLifecycle()
    NewsScreen(uiState = uiState)
}

@Composable
fun NewsScreen(
    uiState: NewsUiState
) {
    when (uiState) {
        NewsUiState.Loading -> {}
        is NewsUiState.HasNews -> {
            PostList(posts = uiState.news)
        }
        is NewsUiState.Failure -> {}
    }
}

@Composable
fun PostList(
    posts: List<News>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState()
    ) {
        items(items = posts, key = { post -> post.id }) { post ->
            PostCardSimple(post = post)
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