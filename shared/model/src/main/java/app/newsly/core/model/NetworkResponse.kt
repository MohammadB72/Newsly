package app.newsly.core.model

data class NetworkResponse<T>(
    val status: String,
    val data: T,
    val error: NetworkError
)