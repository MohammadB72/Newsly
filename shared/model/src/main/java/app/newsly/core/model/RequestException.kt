package app.newsly.core.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkError(
    @SerializedName("key") val key: String? = null,
    @SerializedName("message") val message: String?,
)

data class InAppError(
    val resMessage: Int?
)

data class RequestException(
    val id: UUID = UUID.randomUUID(),
    val networkError: NetworkError? = null,
    val inAppError: InAppError? = null,
    val actionAfterFailure: FailureAction = FailureAction.NONE,
    val retryBlock: () -> Unit = {}
)