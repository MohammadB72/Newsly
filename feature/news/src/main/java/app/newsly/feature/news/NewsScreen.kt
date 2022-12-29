package app.newsly.feature.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.newsly.core.designsystem.component.PostCardSimple
import app.newsly.core.domain.model.News
import app.newsly.core.model.RequestException
import app.newsly.shared.resources.R

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun NewsRoute(
    onPostTapped: (postId: Int) -> Unit,
    onFailureOccurred: @Composable (RequestException) -> Unit,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val uiState by viewModel.newsUiState.collectAsStateWithLifecycle()
    NewsScreen(
        uiState = uiState,
        onPostTapped = onPostTapped,
        onFailureOccurred = onFailureOccurred
    )
}

@Composable
fun NewsScreen(
    uiState: NewsUiState,
    onPostTapped: (postId: Int) -> Unit,
    onFailureOccurred: @Composable (RequestException) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            NewsUiState.Loading -> {
                LoadingContent()
            }
            is NewsUiState.SUCCESS -> {
                SuccessContent(
                    posts = uiState.news,
                    onPostTapped = onPostTapped
                )
            }
            is NewsUiState.Failure -> {
                FailureContent(exception = uiState.exception, onFailureOccurred = onFailureOccurred)
            }
        }
    }
}


@Composable
fun LoadingContent() {
    CircularProgressIndicator()
}

@Composable
fun SuccessContent(
    posts: List<News>,
    onPostTapped: (postId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState()
    ) {
        items(items = posts, key = { post -> post.id }) { post ->
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
fun FailureContent(
    exception: RequestException,
    onFailureOccurred: @Composable (RequestException) -> Unit,
) {
    onFailureOccurred(exception)
    Button(onClick = { exception.retryBlock() }) {
        Text(text = stringResource(id = R.string.retry))
    }
}


@Composable
private fun PostListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}