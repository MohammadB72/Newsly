package app.newsly.core.domain

import app.newsly.core.data.repository.ServerStatusRepository
import app.newsly.core.model.RequestResult
import app.newsly.core.model.domain.ServerStatus
import javax.inject.Inject

class GetServerStatusUseCase @Inject constructor(
    private val serverStatusRepository: ServerStatusRepository
) {

    suspend operator fun invoke(): RequestResult<ServerStatus> {
        return when (val result = serverStatusRepository.getServerStatus()) {
            is RequestResult.Success -> {
                RequestResult.Success(result.data.toDomainModel())
            }
            is RequestResult.Error -> {
                RequestResult.Error(result.exception)
            }
            RequestResult.Loading -> {
                RequestResult.Loading
            }
        }
    }
}