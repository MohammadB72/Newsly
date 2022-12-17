package app.newsly.core.model

data class RequestException(
    val message: String? = null,
    val resMessage: Int = -1,
    val actionAfterFailure: FailureAction = FailureAction.NONE,
    val retryBlock: suspend () -> Unit = {}
)
