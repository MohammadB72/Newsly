package app.newsly.feature.editorchoice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun EditorChoiceRoute() {
    EditorChoiceScreen()
}

@Composable
fun EditorChoiceScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = "Editor Choice",
            textAlign = TextAlign.Center
        )
    }
}