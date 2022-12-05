package app.newsly.feature.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun CategoriesRoute() {
    CategoriesScreen()
}

@Composable
fun CategoriesScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.fillMaxSize(), text = "Categories", textAlign = TextAlign.Center)

    }
}