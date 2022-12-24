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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.newsly.core.model.domain.News
import app.newsly.shared.resources.R
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
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
fun AuthorAndReadTime(
    post: News,
    modifier: Modifier = Modifier
) {
    val shapeSize = 24.dp
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = post.author.avatar,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(shapeSize, shapeSize)
                .clip(CircleShape),
            error = painterResource(id = R.drawable.ic_baseline_account_circle_24),
            colorFilter = if (post.author.avatar.isBlank()) {
                ColorFilter.tint(MaterialTheme.colorScheme.primary)
            } else {
                null
            }
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "${post.author.name} . ${post.date}",
            style = MaterialTheme.typography.labelSmall
        )
    }
}


@Composable
fun PostCardSimple(
    onPostTapped: (id: Int) -> Unit,
    post: News
) {
    Row(
        modifier = Modifier
            .clickable { onPostTapped(post.id) }
            .padding(16.dp)
    ) {
        PostImage(imageUrl = post.image)

        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            PostTitle(title = post.title)
            AuthorAndReadTime(post, modifier = Modifier.padding(top = 16.dp))
        }
    }
}