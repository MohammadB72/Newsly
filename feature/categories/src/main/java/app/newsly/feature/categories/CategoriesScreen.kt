package app.newsly.feature.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.newsly.core.domain.model.Category
import app.newsly.core.model.RequestException
import app.newsly.shared.resources.R
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import java.util.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun CategoriesRoute(
    onCategoryTapped: (categoryId: Int) -> Unit,
    onFailureOccurred: @Composable (RequestException) -> Unit,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.categoriesUiState.collectAsStateWithLifecycle()
    CategoriesScreen(
        uiState = uiState,
        onCategoryTapped = onCategoryTapped,
        onFailureOccurred = onFailureOccurred
    )
}

@Composable
fun CategoriesScreen(
    uiState: CategoriesUiState,
    onCategoryTapped: (categoryId: Int) -> Unit,
    onFailureOccurred: @Composable (RequestException) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        when (uiState) {
            CategoriesUiState.Loading -> {
                LoadingContent()
            }
            is CategoriesUiState.SUCCESS -> {
                SuccessContent(
                    categories = uiState.categories,
                    onCategoryTapped = onCategoryTapped
                )
            }
            is CategoriesUiState.Failure -> {
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
    categories: List<Category>,
    onCategoryTapped: (categoryId: Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)
    ) {
        itemsIndexed(items = categories)
        { index, item ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable {
                        onCategoryTapped(item.id)
                    }
            ) {
                val imageLoader = ImageLoader.Builder(LocalContext.current)
                    .components {
                        add(SvgDecoder.Factory())
                    }
                    .build()

                CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                    val painter = rememberImagePainter(item.icon)

                    Image(
                        painter = painter,
                        contentDescription = "SVG Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color(
                                0f, 0f, 0f, 0.7f
                            )
                        )
                )

                Text(
                    text = " ${item.title}",
                    color = Color.White,
                    fontSize = 28.sp,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                )
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