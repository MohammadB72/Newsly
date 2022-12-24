package app.newsly.feature.newsdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NewsDetailRoute(postId: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            Text(text = "$postId", modifier = Modifier.background(Color.Red))
        }
    }
}