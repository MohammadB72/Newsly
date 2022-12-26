package app.newsly.core.network.retrofit

data class NetworkResponse<T>(
    val status: String,
    val data: T,
    val error: NetworkError
)