package app.newsly.core.model

data class ApiException(
    val message: String? = null,
    val resMessage: Int = -1,
    val errorBehaviour: ErrorBehaviour = ErrorBehaviour.NONE,
    val retry: suspend () -> Unit = {}
)