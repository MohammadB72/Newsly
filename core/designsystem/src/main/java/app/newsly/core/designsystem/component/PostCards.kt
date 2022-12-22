package app.newsly.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.newsly.core.model.domain.News
import coil.compose.AsyncImage


@Composable
fun PostImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(75.dp, 75.dp)
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
fun PostTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
fun AuthorAndReadTime(
    post: News,
    modifier: Modifier = Modifier
) {
    val shapeSize = 20.dp
    Row(modifier) {
        AsyncImage(
            model = post.authorAvatar,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(shapeSize, shapeSize)
                .clip(CircleShape)
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = post.author,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun PostCardSimple(
    post: News
) {
    Row(
        modifier = Modifier
            .clickable { }
            .padding(16.dp)
    ) {
        PostImage(imageUrl = post.image)

        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            PostTitle(title = post.title)
            AuthorAndReadTime(post, modifier = Modifier.padding(top = 16.dp))
        }
    }
}