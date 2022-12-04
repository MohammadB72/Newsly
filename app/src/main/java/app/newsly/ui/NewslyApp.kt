package app.newsly

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import app.newsly.navigation.NewslyNavHost

@Composable
fun NewslyApp() {
    NewslyNavHost(navController = rememberNavController())
}