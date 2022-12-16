package app.newsly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.newsly.core.designsystem.theme.NewslyTheme
import app.newsly.ui.NewslyApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewslyTheme(
                darkTheme = false
            ) {
                NewslyApp()
            }
        }
    }
}