package app.newsly.feature.newsdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.AsyncImage

private val defaultSpacerSize = 16.dp

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun NewsDetailRoute(
    viewModel: NewsDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.newsDetailUiState.collectAsStateWithLifecycle()
    NewsDetailScreen(uiState = uiState)
}

@Composable
fun NewsDetailScreen(
    uiState: NewsDetailUiState,
) {
    when (uiState) {
        NewsDetailUiState.Loading -> {
            LoadingContent()
        }
        is NewsDetailUiState.Success -> {
            SuccessContent(uiState.newsDetail)
        }
        is NewsDetailUiState.Failure -> {
            FailureContent(uiState.exception)
        }
    }
}

@Composable
fun LoadingContent() {

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

        items(items = newsDetail.content) {
            Paragraph(content = it)
        }
    }
}

@Composable
fun Paragraph(content: Content) {
    when (content) {
        is ContentText -> {
            val annotatedString = paragraphToAnnotatedString(
                content,
                MaterialTheme.typography
            )
            Text(text = annotatedString)
        }
        is ContentTextH1 -> {
            Text(text = content.text, style = MaterialTheme.typography.headlineMedium)
        }
        is ContentImage -> {
            PostHeaderImage(imageUrl = content.url)
        }
    }
}

private fun paragraphToAnnotatedString(
    paragraph: ContentText,
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
        MarkupType.Link -> {
            AnnotatedString.Range(
                typography.bodyLarge.copy(textDecoration = TextDecoration.Underline).toSpanStyle(),
                start,
                end
            )
        }
        MarkupType.Bold -> {
            AnnotatedString.Range(
                typography.bodyLarge.copy(fontWeight = FontWeight.Bold).toSpanStyle(),
                start,
                end
            )
        }
        MarkupType.Simple -> {
            AnnotatedString.Range(
                typography.bodyLarge.toSpanStyle(),
                start,
                end
            )
        }
    }
}

@Composable
private fun PostHeaderImage(imageUrl: String) {
    val imageModifier = Modifier
        .height(240.dp)
        .fillMaxWidth()
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = imageModifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun FailureContent(exception: RequestException) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            LazyColumn(
                modifier = Modifier,
                state = rememberLazyListState()
            ) {
            }
        }
    }
}