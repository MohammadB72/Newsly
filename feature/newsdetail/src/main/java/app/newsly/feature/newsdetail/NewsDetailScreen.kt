package app.newsly.feature.newsdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.newsly.core.domain.model.*
import app.newsly.core.domain.model.enums.MarkupType
import app.newsly.core.model.RequestException
import app.newsly.shared.resources.R
import coil.compose.AsyncImage

private val defaultSpacerSize = 16.dp

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun NewsDetailRoute(
    onFailureOccurred: @Composable (RequestException) -> Unit,
    viewModel: NewsDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    NewsDetailScreen(uiState = uiState, onFailureOccurred = onFailureOccurred)
}

@Composable
fun NewsDetailScreen(
    uiState: NewsDetailUiState,
    onFailureOccurred: @Composable (RequestException) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            NewsDetailUiState.Loading -> {
                LoadingContent()
            }
            is NewsDetailUiState.Success -> {
                SuccessContent(uiState.newsDetail)
            }
            is NewsDetailUiState.Failure -> {
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
fun SuccessContent(newsDetail: NewsDetail) {
    LazyColumn(
        modifier = Modifier,
        state = rememberLazyListState(),
    ) {
        item {
            PostHeaderImage(imageUrl = newsDetail.imageUrl)
            Spacer(Modifier.height(defaultSpacerSize))
        }

        items(items = newsDetail.contentList) {
            Paragraph(contentItem = it)
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
fun Paragraph(contentItem: ContentItem) {
    when (contentItem) {
        is ContentItemText -> {
            val annotatedString = paragraphToAnnotatedString(
                contentItem,
                MaterialTheme.typography
            )
            Text(text = annotatedString)
        }
        is ContentItemTextHeadline -> {
            Text(text = contentItem.text, style = MaterialTheme.typography.headlineMedium)
        }
        is ContentItemImage -> {
            ContentImage(imageUrl = contentItem.url)
        }
        is ContentItemUnknown -> {
            Text(
                text = "type: ${contentItem.type}\nattr: ${contentItem.attr}",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.Red
                    )
            )
        }
    }
}

private fun paragraphToAnnotatedString(
    paragraph: ContentItemText,
    typography: Typography
): AnnotatedString {
    val styles: List<AnnotatedString.Range<SpanStyle>> = paragraph.markups
        .map { it.toAnnotatedStringItem(typography) }
    return AnnotatedString(text = paragraph.text, spanStyles = styles)
}

fun Markup.toAnnotatedStringItem(
    typography: Typography,
): AnnotatedString.Range<SpanStyle> {
    return when (this.type) {
        MarkupType.LINK -> {
            AnnotatedString.Range(
                typography.bodyLarge.copy(textDecoration = TextDecoration.Underline).toSpanStyle(),
                start,
                end
            )
        }
        MarkupType.BOLD -> {
            AnnotatedString.Range(
                typography.bodyLarge.copy(fontWeight = FontWeight.Bold).toSpanStyle(),
                start,
                end
            )
        }
        MarkupType.TEXT -> {
            AnnotatedString.Range(
                typography.bodyLarge.toSpanStyle(),
                start,
                end
            )
        }
    }
}

@Composable
private fun PostHeaderImage(imageUrl: String?) {
    val imageModifier = Modifier
        .height(240.dp)
        .fillMaxWidth()
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = imageModifier,
        contentScale = ContentScale.Crop,
        error = painterResource(id = R.drawable.image_preview)
    )
}

@Composable
private fun ContentImage(imageUrl: String?) {
    val imageModifier = Modifier
        .height(240.dp)
        .fillMaxWidth()
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = imageModifier,
        contentScale = ContentScale.Crop,
        error = painterResource(id = R.drawable.image_preview)
    )
}

