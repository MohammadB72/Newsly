package app.newsly.core.model

import java.util.*


data class RequestException(
    val id: UUID = UUID.randomUUID(),
    val key: String? = null,
    val networkErrorMessage: String? = null,
    val inAppErrorMessage: Int = -1,
    val actionAfterFailure: FailureAction = FailureAction.NONE,
    val retryBlock: () -> Unit = {}
)