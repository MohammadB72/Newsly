package app.newsly.core.domain

import app.newsly.core.data.repository.ServerStatusRepository
import app.newsly.core.model.ApiResult
import app.newsly.core.model.domain.ServerStatus
import javax.inject.Inject

class GetServerStatusUseCase @Inject constructor(
    private val serverStatusRepository: ServerStatusRepository
) {

    suspend operator fun invoke(): ApiResult<ServerStatus> {
        return when (val result = serverStatusRepository.getServerStatus()) {
            is ApiResult.Success -> {
                ApiResult.Success(result.data.toDomainModel())
            }
            is ApiResult.Error -> {
                ApiResult.Error(result.exception)
            }
            ApiResult.Loading -> {
                ApiResult.Loading
            }
        }
    }
}