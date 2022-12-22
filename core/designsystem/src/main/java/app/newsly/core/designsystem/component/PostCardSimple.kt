package app.newsly.core.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import app.newsly.core.model.domain.News

@Composable
fun PostCardSimple(
    post: News
) {
    Row {
        Text(text = post.title)
    }
}