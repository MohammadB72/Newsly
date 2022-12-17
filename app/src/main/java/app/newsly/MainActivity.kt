package app.newsly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import app.newsly.core.designsystem.theme.NewslyTheme
import app.newsly.core.model.FailureAction
import app.newsly.shared.resources.R
import app.newsly.ui.NewslyNavgraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewslyTheme(
                darkTheme = false
            ) {
                val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

                Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) })
                { paddingValues ->
                    paddingValues
                    NewslyNavgraph(onFailureOccured = { exception ->
                        if (exception.actionAfterFailure == FailureAction.SHOW_SNACK) {
                            LaunchedEffect(snackbarHostState)
                            {
                                val snackbarResult = snackbarHostState.showSnackbar(
                                    exception.message.toString(),
                                    actionLabel = getString(R.string.retry)
                                )
                                if (snackbarResult == SnackbarResult.ActionPerformed) {
                                    exception.retryBlock()
                                }
                            }

                        }
                    })
                }
            }
        }
    }
}