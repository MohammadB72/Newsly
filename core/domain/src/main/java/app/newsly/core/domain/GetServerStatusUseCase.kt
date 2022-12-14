package app.newsly.core.domain

import app.newsly.core.data.repository.ServerStatusRepository
import app.newsly.core.model.Result
import app.newsly.core.model.domain.ServerStatus
import app.newsly.core.model.network.ServerStatusNetworkModel
import javax.inject.Inject

class GetServerStatusUseCase @Inject constructor(
    private val serverStatusRepository: ServerStatusRepository
) : BaseUseCase<ServerStatusNetworkModel, ServerStatus>() {

    override suspend fun execute(): Result<ServerStatusNetworkModel> =
        serverStatusRepository.getServerStatus()

    override fun mapper(apiModel: ServerStatusNetworkModel): ServerStatus =
        apiModel.toDomainModel()
}