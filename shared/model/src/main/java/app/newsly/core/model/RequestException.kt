package app.newsly.core.model

import java.util.*

data class RequestException(
    val id: UUID = UUID.randomUUID(),
    val message: String? = null,
    val resMessage: Int = -1,
    val actionAfterFailure: FailureAction = FailureAction.NONE,
    val retryBlock: () -> Unit = {}
)
