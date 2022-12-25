package app.newsly.feature.newsdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewsDetailRoute(
    postId: Int,
    viewModel: NewsDetailViewModel = hiltViewModel()
) {
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