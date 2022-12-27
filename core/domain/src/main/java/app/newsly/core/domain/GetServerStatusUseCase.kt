package app.newsly.core.domain

import app.newsly.core.data.repository.serverstatus.ServerStatusRepository
import app.newsly.core.domain.model.EmptyResponse
import app.newsly.core.model.RequestResult
import app.newsly.core.model.doOnFailure
import app.newsly.core.model.doOnLoading
import app.newsly.core.model.doOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetServerStatusUseCase @Inject constructor(
    private val serverStatusRepository: ServerStatusRepository
) {
    operator fun invoke(): Flow<RequestResult<EmptyResponse>> {
        return flow {
            emit(RequestResult.Loading)
            serverStatusRepository
                .getServerStatus()
                .doOnLoading { emit(RequestResult.Loading) }
                .doOnSuccess { data -> emit(RequestResult.Success(EmptyResponse())) }
                .doOnFailure { exception -> emit(RequestResult.Fail(exception)) }
        }
    }
}

