package app.newsly.core.data.repository

import app.newsly.core.model.Result
import app.newsly.core.model.network.ServerStatusNetworkModel
import app.newsly.core.network.RemoteDataSource
import javax.inject.Inject

class ServerStatusRepositoryImp @Inject constructor(
    private val networkDataSource: RemoteDataSource
) : ServerStatusRepository {

    override suspend fun getServerStatus(): Result<ServerStatusNetworkModel> =
        networkDataSource.getServerStatus()
}