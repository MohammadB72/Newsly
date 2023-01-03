package app.newsly.feature.subcategories

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.newsly.core.designsystem.component.PostCardSimple

@Composable
fun NewsContentScreen(
    modifier: Modifier = Modifier,
    subCategoriesNewsUiState: SubCategoriesNewsUiState.Success,
    onPostTapped: (postId: Int) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState()
    ) {
        items(items = subCategoriesNewsUiState.newsList, key = { post -> post.id }) { post ->
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